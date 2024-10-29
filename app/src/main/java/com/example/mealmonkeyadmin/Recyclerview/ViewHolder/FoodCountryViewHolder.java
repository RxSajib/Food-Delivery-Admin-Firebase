package com.example.mealmonkeyadmin.Recyclerview.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.databinding.CountryitemlayoutBinding;

public class FoodCountryViewHolder extends RecyclerView.ViewHolder {

    public CountryitemlayoutBinding binding;

    public FoodCountryViewHolder(@NonNull CountryitemlayoutBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
