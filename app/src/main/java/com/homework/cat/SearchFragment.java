package com.homework.cat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchFragment  extends Fragment {
    private EditText catEdit;
    private ImageView searchIv;
    private RecyclerView mRecyclerView;
    private ArrayList<Breed> breeds = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_search, container, false);
         catEdit = (EditText) view.findViewById(R.id.cat_et);
        searchIv =  (ImageView) view.findViewById(R.id.search_iv);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.listview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final SearchAdapter adapter = new SearchAdapter(getActivity(), breeds);
        mRecyclerView.setAdapter(adapter);
        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://api.thecatapi.com/v1/breeds/search?q=sib
                String url = "https://api.thecatapi.com/v1/breeds/search?q="+catEdit.getText().toString().trim();
                RequestQueue newRequestQueue = Volley.newRequestQueue(getActivity());
                StringRequest request = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    Gson gson = new Gson();
                                    Breed[] objectsArray = gson.fromJson(response, Breed[].class);
                                    List<Breed> list = Arrays.asList(objectsArray);
                                    breeds.clear();
                                    breeds.addAll(list) ;
                                    adapter.notifyDataSetChanged();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                newRequestQueue.add(request);
            }
        });
        return view;
    }
}
