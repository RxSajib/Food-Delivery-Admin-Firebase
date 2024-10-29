package com.example.mealmonkeyadmin.Recyclerview.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.databinding.OrderfooditemBinding;

public class OrderFoodVH extends RecyclerView.ViewHolder {

    public OrderfooditemBinding binding;

    public OrderFoodVH(@NonNull OrderfooditemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
