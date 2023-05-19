package com.example.persistence;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlowerViewAdapter extends RecyclerView.Adapter<FlowerViewAdapter.FlowerViewHolder> {

    private List<Flower> flowerList;

    public FlowerViewAdapter(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_layout, parent, false);
        return new FlowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {
        Flower flower = flowerList.get(position);

        holder.bind(flower);
        if((position % 2) == 0) {
            holder.itemView.setBackgroundResource(R.color.lightgrey);
        }else{
            holder.itemView.setBackgroundResource(R.color.lightblue);
        }

    }

    @Override
    public int getItemCount() {
        return flowerList.size();
    }

    public static class FlowerViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView speciesTextView;
        private TextView genusTextView;

        public FlowerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.showname);
            speciesTextView = itemView.findViewById(R.id.showspecies);
            genusTextView = itemView.findViewById(R.id.showgenus);
        }

        public void bind(Flower flower) {
            nameTextView.setText(flower.getName());
            speciesTextView.setText(flower.getSpecies());
            genusTextView.setText(flower.getGenus());
        }
    }
}

