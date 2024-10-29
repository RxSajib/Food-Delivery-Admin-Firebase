package com.example.mealmonkeyadmin.Network.Repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealmonkeyadmin.Data.DataManager;
import com.example.mealmonkeyadmin.Data.Model.RestaurantModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class RestaurantsGET {

    private Application application;
    private MutableLiveData<List<RestaurantModel>> data;
    private CollectionReference RestaurantsRef;

    public RestaurantsGET(Application application){
        this.application = application;
        RestaurantsRef = FirebaseFirestore.getInstance().collection(DataManager.RestaurantRef);
    }

    public LiveData<List<RestaurantModel>> getRestaurants(){
        data = new MutableLiveData<>();
        RestaurantsRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    return;
                }
                if(!value.isEmpty()){
                    for (DocumentChange ds : value.getDocumentChanges()){
                        if(ds.getType() == DocumentChange.Type.ADDED || ds.getType() == DocumentChange.Type.MODIFIED || ds.getType() == DocumentChange.Type.REMOVED){
                            data.setValue(value.toObjects(RestaurantModel.class));
                        }
                    }
                }else {
                    data.setValue(null);
                }
            }
        });

        return data;
    }
}
