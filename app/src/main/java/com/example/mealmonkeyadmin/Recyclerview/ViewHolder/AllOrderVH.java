package com.example.mealmonkeyadmin.Recyclerview.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.R;

public class AllOrderVH extends RecyclerView.ViewHolder {

    public TextView FirstName, LastName, PhoneNumber, Location, ID;

    public AllOrderVH(@NonNull View itemView) {
        super(itemView);

        FirstName = itemView.findViewById(R.id.FirstName);
        LastName = itemView.findViewById(R.id.LastName);
        PhoneNumber = itemView.findViewById(R.id.Number);
        Location = itemView.findViewById(R.id.Location);
        ID = itemView.findViewById(R.id.OrderID);
    }
}
