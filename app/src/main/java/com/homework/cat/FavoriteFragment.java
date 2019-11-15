package com.homework.cat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Favourite> favourites = new ArrayList<>();
    private FavouriteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.listview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
          adapter = new FavouriteAdapter(getActivity(), favourites);
        mRecyclerView.setAdapter(adapter);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        List<Favourite> list = FavouriteUtils.getAllFavourite();
        favourites.clear();
        favourites.addAll(list);
        adapter.notifyDataSetChanged();

    }
}
