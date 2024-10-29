package com.example.mealmonkeyadmin.Recyclerview.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.Data.Model.FoodType;
import com.example.mealmonkeyadmin.Recyclerview.ViewHolder.FoodTypeViewHolder;
import com.example.mealmonkeyadmin.databinding.FoodtypeitemlayoutBinding;

import java.util.List;

public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeViewHolder> {

    private List<FoodType> foodTypeList;
    private LayoutInflater layoutInflater;
    private OnClick OnClick;

    public FoodTypeAdapter(List<FoodType> foodTypeList) {
        this.foodTypeList = foodTypeList;
    }

    @NonNull
    @Override
    public FoodTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        FoodtypeitemlayoutBinding binding = FoodtypeitemlayoutBinding.inflate(layoutInflater, parent, false);
        return new FoodTypeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodTypeViewHolder holder, int position) {
        holder.binding.TypeText.setText(foodTypeList.get(position).getTitle());
        holder.binding.Icon.setImageResource(foodTypeList.get(position).getIcon());

        holder.itemView.setOnClickListener(v ->{
            OnClick.Click(foodTypeList.get(position).getTitle());
        });
    }

    @Override
    public int getItemCount() {
        if(foodTypeList == null){
            return 0;
        }else {
            return foodTypeList.size();
        }
    }

    public interface OnClick{
        void Click(String Type);
    }

    public void SetOnClickLisiner(OnClick OnClick){
        this.OnClick = OnClick;
    }
}
