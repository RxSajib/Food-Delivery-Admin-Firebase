package com.example.mealmonkeyadmin.UI.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.mealmonkeyadmin.Data.Model.OrderItem;
import com.example.mealmonkeyadmin.Data.Model.OrderModel;
import com.example.mealmonkeyadmin.Network.NetworkViewModel.ViewModel;
import com.example.mealmonkeyadmin.R;
import com.example.mealmonkeyadmin.Recyclerview.Adapter.FoodOrderAdapter;
import com.example.mealmonkeyadmin.databinding.OrderproductBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class OrderProduct extends AppCompatActivity {

    private OrderproductBinding binding;
    private OrderModel orderModel;
    private ViewModel viewModel;
    private FoodOrderAdapter foodOrderAdapter;
    private CollectionReference OrderRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.orderproduct);
        OrderRef = FirebaseFirestore.getInstance().collection("Order");

        orderModel = (OrderModel) getIntent().getSerializableExtra("Data");
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        foodOrderAdapter = new FoodOrderAdapter();


        InitView();
        GetData();
    }

    private void GetData(){
        viewModel.GetFoodOrderItem(String.valueOf(orderModel.getID())).observe(this, new Observer<List<OrderItem>>() {
            @Override
            public void onChanged(List<OrderItem> orderItems) {
                if(orderItems != null){
                    foodOrderAdapter.setList(orderItems);
                    foodOrderAdapter.notifyDataSetChanged();
                }else {
                    foodOrderAdapter.setList(null);
                    foodOrderAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void InitView(){
        foodOrderAdapter.OnClickState((orderItem, position) -> {

            var d = new AlertDialog.Builder(OrderProduct.this);
            d.setTitle("Are you sure");
            d.setMessage("Do you want to delivered it");
            d.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                    OrderRef.document(String.valueOf(orderModel.getID()))
                            .update("Items", FieldValue.arrayRemove(new OrderItem(orderItem.getFoodLocation(), orderItem.getFoodMadeBy(), orderItem.getFoodTitle(), orderItem.getImageOne(), orderItem.getFoodID(), orderItem.getQuantity(),
                                    orderItem.getRating(), orderItem.getStatus(), orderItem.getTotalPrice(), orderItem.getFoodPrice())))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        OrderRef.document(String.valueOf(orderModel.getID()))
                                                .update("Items", FieldValue.arrayUnion(new OrderItem(orderItem.getFoodLocation(), orderItem.getFoodMadeBy(), orderItem.getFoodTitle(), orderItem.getImageOne(), orderItem.getFoodID(), orderItem.getQuantity(),
                                                        orderItem.getRating(), "Delivered", orderItem.getTotalPrice(), orderItem.getFoodPrice())));


                                    }else {
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });

                }
            });
            d.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            var al = d.create();
            al.show();
        });
        binding.RecyclerView.setHasFixedSize(true);
        binding.RecyclerView.setAdapter(foodOrderAdapter);

        binding.BackButton.setOnClickListener(v ->{
            finish();
            Animatoo.animateSlideRight(OrderProduct.this);
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        Animatoo.animateSlideRight(OrderProduct.this);
    }
}