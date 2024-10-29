package com.example.mealmonkeyadmin.Recyclerview.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.Data.Model.OrderItem;
import com.example.mealmonkeyadmin.R;
import com.example.mealmonkeyadmin.Recyclerview.ViewHolder.OrderFoodVH;
import com.example.mealmonkeyadmin.databinding.OrderfooditemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodOrderAdapter extends RecyclerView.Adapter<OrderFoodVH> {

    private List<OrderItem> list;

    public List<OrderItem> getList() {
        return list;
    }

    public void setList(List<OrderItem> list) {
        this.list = list;
    }
    private OnClick OnClick;

    @NonNull
    @Override
    public OrderFoodVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var  l = LayoutInflater.from(parent.getContext());
        var v = OrderfooditemBinding.inflate(l, parent, false);
        return new OrderFoodVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderFoodVH holder, int position) {
        Picasso.get().load(list.get(position).getImageOne()).placeholder(R.color.carbon_black_6).into(holder.binding.ImageView);
        holder.binding.FoodTitle.setText(list.get(position).getFoodTitle());
        holder.binding.FoodMadeBy.setText(list.get(position).getFoodMadeBy());
        holder.binding.RatingNumber.setText(String.valueOf(list.get(position).getQuantity()));
        holder.binding.Price.setText("\u09F3 "+list.get(position).getFoodPrice());


        var st = list.get(position).getStatus();
        if(st.equals("Pending")){
            holder.binding.PendingBtn.setBackgroundResource(R.drawable.pendingbg);
            holder.binding.Status.setText("Pending");
        }else {
            holder.binding.Status.setText("Delivered");
            holder.binding.PendingBtn.setBackgroundResource(R.drawable.deliveredbg);
        }

        holder.binding.PendingBtn.setOnClickListener(view -> {
            var status = list.get(position).getStatus();
            if(status.equals("Pending")){
                OnClick.Click(list.get(position), position);
            }else {
                Toast.makeText(holder.itemView.getContext(), "Already delivered", Toast.LENGTH_LONG).show();
            }

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
        void Click(OrderItem orderItem, int position);
    }
    public void OnClickState(OnClick OnClick){
        this.OnClick = OnClick;
    }
}
