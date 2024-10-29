package com.example.mealmonkeyadmin.Network.Repository;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealmonkeyadmin.Data.Model.OrderItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OrderFoodGET {

    private Application application;
    private MutableLiveData<List<OrderItem>> data;
    private FirebaseAuth Mauth;
    private CollectionReference OrderRef;
    private List<OrderItem> list;

    public OrderFoodGET(Application application){
        this.application = application;
        Mauth = FirebaseAuth.getInstance();
        OrderRef = FirebaseFirestore.getInstance().collection("Order");
        list = new ArrayList<>();
    }

    public LiveData<List<OrderItem>> OrderItem(String ID){
        data = new MutableLiveData<>();
        OrderRef.document(ID).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    data.setValue(null);
                    return;
                }
                if(value.exists()){
                    list.clear();
                    List<Map<String, OrderItem>> map = (List<Map<String, OrderItem>>) value.get("Items");
                    for(var valdata : map){
                        var mydata = new OrderItem(String.valueOf(valdata.get("foodLocation")), String.valueOf(valdata.get("foodMadeBy")),
                                String.valueOf(valdata.get("foodTitle")), String.valueOf(valdata.get("imageOne")),
                                Long.valueOf(String.valueOf(valdata.get("foodID"))), Integer.valueOf(String.valueOf(valdata.get("quantity"))),
                                Float.parseFloat(String.valueOf(valdata.get("rating"))),String.valueOf(valdata.get("status")),
                                Integer.valueOf(String.valueOf(valdata.get("totalPrice"))), String.valueOf(valdata.get("foodPrice")));

                        list.add(mydata);
                        data.setValue(list);
                    }
                }
            }
        });
        return data;
    }
}
