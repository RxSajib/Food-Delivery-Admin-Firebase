package com.example.mealmonkeyfooddeleveryjava_admin.Views.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.R;

public class NavagationViewHolder extends RecyclerView.ViewHolder {

    public ImageView NavIcon;
    public TextView NavTitle;

    public NavagationViewHolder(@NonNull View itemView) {
        super(itemView);

        NavIcon = itemView.findViewById(R.id.Drawericon);
        NavTitle = itemView.findViewById(R.id.DrawerTitle);
    }
}
