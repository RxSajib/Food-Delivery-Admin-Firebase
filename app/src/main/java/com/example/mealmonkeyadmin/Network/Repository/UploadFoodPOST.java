package com.example.mealmonkeyadmin.Network.Repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealmonkeyadmin.Data.DataManager;
import com.example.mealmonkeyadmin.Data.Response.NetworkResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UploadFoodPOST {

    private Application application;
    private MutableLiveData<NetworkResponse> data;
    private NetworkResponse networkResponse;
    private CollectionReference MFoodRef;

    public UploadFoodPOST(Application application){
        this.application = application;
        networkResponse = new NetworkResponse();
        MFoodRef = FirebaseFirestore.getInstance().collection(DataManager.AllFood);
    }

    public LiveData<NetworkResponse> UploadFood(String Title, String FoodDetails, String FoodType, String FoodLocation, String RestaurantsLocation, String FoodMadeBy, String FoodPrice, String FoodOffer, String Promotion, String ImageOne, String ImageTwo, String ImageThree, String ImageFour, String RestaurantsName){
        data = new MutableLiveData<>();
        long Timestamp = System.currentTimeMillis();
        data = new MutableLiveData<>();
        Map<String, Object> map = new HashMap<>();
        map.put(DataManager.FoodTitle, Title);
        map.put(DataManager.FoodDetails, FoodDetails);
        map.put(DataManager.FoodType, FoodType);
        map.put(DataManager.FoodLocation, FoodLocation);
        map.put(DataManager.RestaurantsLocation, RestaurantsLocation);
        map.put(DataManager.FoodMadeBy, FoodMadeBy);
        map.put(DataManager.FoodPrice, FoodPrice);
        map.put(DataManager.FoodOffer, FoodOffer);
        map.put(DataManager.Promotions, Promotion);
        map.put(DataManager.ImageOne, ImageOne);
        map.put(DataManager.ImageTwo, ImageTwo);
        map.put(DataManager.ImageThree, ImageThree);
        map.put(DataManager.ImageFour, ImageFour);
        map.put(DataManager.Timestamp, Timestamp);
        map.put(DataManager.FoodID, Timestamp);
        map.put(DataManager.Rating, 0.0);
        map.put(DataManager.FoodNoOFSelling, 0);
        map.put(DataManager.RestaurantsName, RestaurantsName);
        map.put(DataManager.Search, Title.toLowerCase());

        MFoodRef.document(String.valueOf(Timestamp))
                .set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    networkResponse.setCode(DataManager.SUCCESS_CODE);
                    data.setValue(networkResponse);
                }else {
                    networkResponse.setCode(DataManager.ERROR_CODE);
                    data.setValue(networkResponse);
                    Toast.makeText(application, "Unable to upload "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                networkResponse.setCode(DataManager.ERROR_CODE);
                data.setValue(networkResponse);
                Toast.makeText(application, "Unable to upload "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }

}
