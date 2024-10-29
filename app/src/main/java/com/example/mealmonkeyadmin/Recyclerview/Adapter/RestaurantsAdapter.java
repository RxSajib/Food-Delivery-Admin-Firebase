package com.example.mealmonkeyadmin.Recyclerview.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.Data.Model.RestaurantModel;
import com.example.mealmonkeyadmin.R;
import com.example.mealmonkeyadmin.Recyclerview.ViewHolder.RestaurantsViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsViewHolder> {

    private OnClickItem OnClickItem;
    private List<RestaurantModel> list;

    public void setList(List<RestaurantModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RestaurantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestaurantsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.restrounts_iteam, parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsViewHolder holder, int position) {
        holder.Title.setText(list.get(position).getRestaurantTitle());
        holder.Details.setText(list.get(position).getRestaurantDetails());
        Picasso.get().load(list.get(position).getRestaurantImageUri()).into(holder.RestaurantsImage);

        holder.itemView.setOnClickListener(v -> {
            OnClickItem.Click(list.get(position).getRestaurantTitle());
        });
    }

    @Override
    public int getItemCount() {
        if(list == null){
            return 0;
        }else {
            return list.size();
        }
    }

    public interface OnClickItem{
        void Click(String Title);
    }

    public void OnItmClick(OnClickItem OnClickItem){
        this.OnClickItem = OnClickItem;
    }
}
