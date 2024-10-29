package com.example.mealmonkeyadmin.Recyclerview.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.Data.Model.OrderModel;
import com.example.mealmonkeyadmin.Recyclerview.ViewHolder.AllOrderVH;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<AllOrderVH> {

    private List<OrderModel> list;
    private OnClick OnClick;

    public List<OrderModel> getList() {
        return list;
    }

    public void setList(List<OrderModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AllOrderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var l= LayoutInflater.from(parent.getContext());
        var v = l.inflate(com.example.mealmonkeyadmin.R.layout.orderitem, parent, false);
        return new AllOrderVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AllOrderVH holder, int position) {
        holder.ID.setText(String.valueOf("ID: "+String.valueOf(list.get(position).getID())));
        holder.FirstName.setText(String.valueOf(list.get(position).getFirstName()));
        holder.LastName.setText(String.valueOf(list.get(position).getLastName()));
        holder.PhoneNumber.setText(String.valueOf(list.get(position).getPhoneNumber()));
        holder.Location.setText(String.valueOf(list.get(position).getArea()+" "+list.get(position).getCity()));

        holder.itemView.setOnClickListener(view -> {
            OnClick.Click(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        if(list == null) {
            return 0;
        }else {
            return list.size();
        }
    }

    public interface OnClick{
        void Click(OrderModel orderModel);
    }
    public void OnClickState(OnClick OnClick){
        this.OnClick = OnClick;
    }
}
