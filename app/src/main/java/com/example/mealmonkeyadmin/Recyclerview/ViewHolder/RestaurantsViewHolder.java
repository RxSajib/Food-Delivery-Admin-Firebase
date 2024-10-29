package com.example.mealmonkeyadmin.Recyclerview.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class RestaurantsViewHolder extends RecyclerView.ViewHolder {

    public TextView Title, Details;
    public CircleImageView RestaurantsImage;

    public RestaurantsViewHolder(@NonNull View itemView) {
        super(itemView);

        Title = itemView.findViewById(R.id.Title);
        Details = itemView.findViewById(R.id.Details);
        RestaurantsImage = itemView.findViewById(R.id.ImageView);
    }
}
