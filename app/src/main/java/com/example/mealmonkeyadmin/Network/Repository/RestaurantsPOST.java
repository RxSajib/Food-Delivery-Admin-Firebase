package com.example.mealmonkeyadmin.Network.Repository;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealmonkeyadmin.Data.DataManager;
import com.example.mealmonkeyadmin.Data.Response.NetworkResponse;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RestaurantsPOST {

    private Application application;
    private MutableLiveData<NetworkResponse> data;
    private NetworkResponse networkResponse;
    public FirebaseFirestore MRestaurantRef;

    public RestaurantsPOST(Application application) {
        this.application = application;
        data = new MutableLiveData<>();
        MRestaurantRef = FirebaseFirestore.getInstance();
        networkResponse = new NetworkResponse();
    }

    public LiveData<NetworkResponse> UploadRestaurantsData(String ImageUri, String Title, String Details) {
        long Timestamp = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        map.put(DataManager.RestaurantTitle, Title);
        map.put(DataManager.RestaurantDetails, Details);
        map.put(DataManager.RestaurantImageUri, ImageUri);
        map.put(DataManager.Timestamp, Timestamp);

        MRestaurantRef.collection(DataManager.RestaurantRef)
                .document().set(map)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        networkResponse.setCode(DataManager.SUCCESS_CODE);
                        data.setValue(networkResponse);
                    } else {
                        networkResponse.setCode(DataManager.ERROR_CODE);
                        data.setValue(networkResponse);
                        Toast.makeText(application, "Error uploading data", Toast.LENGTH_SHORT).show();
                    }

                }).addOnFailureListener(e -> {
            networkResponse.setCode(DataManager.ERROR_CODE);
            data.setValue(networkResponse);
            Toast.makeText(application, "Error uploading data", Toast.LENGTH_SHORT).show();
        });
        return data;
    }
}
