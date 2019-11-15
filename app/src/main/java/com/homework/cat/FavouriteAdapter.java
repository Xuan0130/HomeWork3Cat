package com.homework.cat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

    private ArrayList<Favourite> breedArrayList;
    private Context context;

    public FavouriteAdapter(Context context, ArrayList<Favourite> breedArrayList) {
        this.breedArrayList = breedArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Favourite breed = breedArrayList.get(position);
        String name = breed.getCatName();
        final String id = breed.getCatId();
        holder.nameText.setText(name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BreedDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("breedID", id);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return breedArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.name_textview);


        }

    }
}
