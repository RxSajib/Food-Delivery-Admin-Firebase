package com.example.mealmonkeyadmin.Network.Repository;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealmonkeyadmin.Data.Model.OrderModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class OrderDetailsGET {

    private Application application;
    private MutableLiveData<List<OrderModel>> data;
    private CollectionReference OrderRef;
    private FirebaseAuth Mauth;

    public OrderDetailsGET(Application application){
        this.application = application;
        OrderRef = FirebaseFirestore.getInstance().collection("Order");
        Mauth = FirebaseAuth.getInstance();
    }

    public LiveData<List<OrderModel>> GetOrderDetails(){
        data = new MutableLiveData<>();
        var FirebaseUsr = Mauth.getCurrentUser();
        if(FirebaseUsr != null){
            OrderRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if(error != null){
                        data.setValue(null);
                        return;
                    }
                    if(!value.isEmpty()){
                        for(var ds : value.getDocumentChanges()){
                            if(ds.getType() == DocumentChange.Type.ADDED || ds.getType() == DocumentChange.Type.MODIFIED || ds.getType() == DocumentChange.Type.REMOVED){
                                data.setValue(value.toObjects(OrderModel.class));
                            }
                        }
                    }else {
                        data.setValue(null);
                    }
                }
            });
        }
        return data;
    }
}
