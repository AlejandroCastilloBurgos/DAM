package com.example.recicleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// CandyAdapter.java
public class CandyAdapter extends RecyclerView.Adapter<CandyAdapter.ViewHolder> {
    private List<Candy> candyList;

    public CandyAdapter(List<Candy> candyList) {
        this.candyList = candyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Candy candy = candyList.get(position);
        holder.tvName.setText(candy.getName());
        holder.tvHasNuts.setText("Has Nuts: " + (candy.hasNuts() ? "Yes" : "No"));
        holder.tvCalories.setText("Calories: " + candy.getCalories());
    }

    @Override
    public int getItemCount() {
        return candyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvHasNuts, tvCalories;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvHasNuts = itemView.findViewById(R.id.tvHasNuts);
            tvCalories = itemView.findViewById(R.id.tvCalories);
        }
    }
}
