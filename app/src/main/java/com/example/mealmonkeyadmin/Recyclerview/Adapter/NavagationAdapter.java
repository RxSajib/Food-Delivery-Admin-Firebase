package com.example.mealmonkeyadmin.Recyclerview.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealmonkeyadmin.Data.Model.NavigationView;
import com.example.mealmonkeyadmin.R;
import com.example.mealmonkeyadmin.Recyclerview.ViewHolder.NavagationViewHolder;

import java.util.List;

public class NavagationAdapter extends RecyclerView.Adapter<NavagationViewHolder> {

    private List<NavigationView> navdata;
    private OnClickIteam OnClickIteam;

    public NavagationAdapter(List<NavigationView> navdata) {
        this.navdata = navdata;
    }

    @NonNull
    @Override
    public NavagationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NavagationViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.navagation_single_view, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NavagationViewHolder holder, int position) {
        holder.NavTitle.setText(navdata.get(position).Title);
        holder.NavIcon.setImageResource(navdata.get(position).Icon);

        holder.itemView.setOnClickListener(view -> {
           OnClickIteam.Position(holder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        if(navdata == null){
            return 0;
        }else {
            return navdata.size();
        }
    }

    public interface OnClickIteam{
        void Position(int position);
    }

    public void SetOnclickLisiner(OnClickIteam OnClickIteam){
        this.OnClickIteam = OnClickIteam;
    }
}
