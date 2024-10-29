package com.example.mealmonkeyadmin.Recyclerview.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.Data.Model.FoodType;
import com.example.mealmonkeyadmin.Recyclerview.ViewHolder.FoodCountryViewHolder;
import com.example.mealmonkeyadmin.databinding.CountryitemlayoutBinding;

import java.util.List;

public class FoodCountryAdapter extends RecyclerView.Adapter<FoodCountryViewHolder> {

    private List<FoodType> foodTypeList;
    private LayoutInflater layoutInflater;
    private OnClick OnClick;

    public FoodCountryAdapter(List<FoodType> foodTypeList) {
        this.foodTypeList = foodTypeList;
    }

    @NonNull
    @Override
    public FoodCountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        CountryitemlayoutBinding binding = CountryitemlayoutBinding.inflate(layoutInflater, parent, false);
        return new FoodCountryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCountryViewHolder holder, int position) {
        holder.binding.Icon.setImageResource(foodTypeList.get(position).getIcon());
        holder.binding.CountryText.setText(foodTypeList.get(position).getTitle());

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
        void Click(String Text);
    }
    public void SetOnClickLisiner(OnClick OnClick){
        this.OnClick = OnClick;
    }
}
