package com.homework.cat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class BreedDetailActivity extends AppCompatActivity {
    private Favourite favourite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
       Intent intent =  getIntent();
       Bundle bundle = intent.getExtras();
        String breedId = bundle.getString("breedID");
        getDetail(breedId);
    }

    private void getDetail(final String breedId) {
        //https://api.thecatapi.com/v1/images/search?breed_id=beng
        String url = "https://api.thecatapi.com/v1/images/search?breed_id="+breedId;
        RequestQueue newRequestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Gson gson = new Gson();
                            BreedDetail[] objectsArray = gson.fromJson(response, BreedDetail[].class);
                            List<BreedDetail> list = Arrays.asList(objectsArray);
                            BreedDetail breedDetail = list.get(0);
                            ImageView catImage = findViewById(R.id.catImage);
                            TextView catName = findViewById(R.id.catName);
                            TextView catDes = findViewById(R.id.catDes);
                            TextView catWeight = findViewById(R.id.catWeight);
                            TextView catTemperament = findViewById(R.id.catTemperament);
                            TextView catOrigin = findViewById(R.id.catOrigin);
                            TextView catLifeSpan = findViewById(R.id.catLifeSpan);
                            TextView catWikiUrl = findViewById(R.id.catWikiUrl);
                            TextView catDogFriendly = findViewById(R.id.catDogFriendly);

                            Glide.with(BreedDetailActivity.this)
                                    .load(breedDetail.getUrl()).into(catImage);

                            Breed breed = breedDetail.getBreeds().get(0);
                            String id = breed.getId();
                            String name = breed.getName();
                            String description = breed.getDescription();
                            String metric = breed.getWeight().getMetric();
                            String breedTemperament = breed.getTemperament();
                            String origin = breed.getOrigin();
                            String lifeSpan = breed.getLife_span();
                            String wikipediaUrl = breed.getWikipedia_url();
                            String dogFriendly = breed.getDog_friendly()+"";
                            catName.setText(name);
                            catDes.setText(description);
                            catTemperament.setText(breedTemperament);
                            catWeight.setText(metric);
                            catOrigin.setText(origin);
                            catLifeSpan.setText(lifeSpan);
                            catWikiUrl.setText(wikipediaUrl);
                            catDogFriendly.setText(dogFriendly);
                            favourite = new Favourite(id,name);
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

    public void addFavourite(View view) {
        if (favourite==null){
            return;
        }
        FavouriteUtils.addFavourite(favourite);
        Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show();
    }
}
