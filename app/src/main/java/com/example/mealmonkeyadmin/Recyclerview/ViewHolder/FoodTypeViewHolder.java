package com.example.mealmonkeyadmin.Recyclerview.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.databinding.FoodtypeitemlayoutBinding;

public class FoodTypeViewHolder extends RecyclerView.ViewHolder {

    public FoodtypeitemlayoutBinding binding;

    public FoodTypeViewHolder(@NonNull FoodtypeitemlayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
