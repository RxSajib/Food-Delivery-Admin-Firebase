package com.example.mealmonkeyadmin.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.mealmonkeyadmin.Data.Model.OrderModel;
import com.example.mealmonkeyadmin.Network.NetworkViewModel.ViewModel;
import com.example.mealmonkeyadmin.R;
import com.example.mealmonkeyadmin.Recyclerview.Adapter.OrderAdapter;
import com.example.mealmonkeyadmin.databinding.AllorderBinding;

import java.util.List;

public class AllOrder extends AppCompatActivity {

    private AllorderBinding binding;
    private OrderAdapter orderAdapter;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.allorder);
        orderAdapter = new OrderAdapter();
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        InitView();
        GetData();
    }

    private void GetData(){
        viewModel.GetOrder().observe(this, new Observer<List<OrderModel>>() {
            @Override
            public void onChanged(List<OrderModel> orderModels) {
                if(orderModels != null){
                    orderAdapter.setList(orderModels);
                    orderAdapter.notifyDataSetChanged();
                }else {
                    orderAdapter.setList(null);
                    orderAdapter.notifyDataSetChanged();
                }
            }
        });

        orderAdapter.OnClickState(new OrderAdapter.OnClick() {
            @Override
            public void Click(OrderModel orderModel) {
                var intent = new Intent(getApplicationContext(), OrderProduct.class);
                intent.putExtra("Data", orderModel);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity(intent);
                Animatoo.animateSlideLeft(AllOrder.this);
            }
        });
    }
    private void InitView(){
        binding.RecyclerView.setHasFixedSize(true);
        binding.RecyclerView.setAdapter(orderAdapter);

        binding.BackButton.setOnClickListener(v ->{
            finish();
            Animatoo.animateSlideRight(AllOrder.this);
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        Animatoo.animateSlideRight(AllOrder.this);
    }
}