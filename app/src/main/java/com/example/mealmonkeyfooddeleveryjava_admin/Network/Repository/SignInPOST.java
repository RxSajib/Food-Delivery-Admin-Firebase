package com.example.mealmonkeyfooddeleveryjava_admin.Network.Repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealmonkeyfooddeleveryjava_admin.Data.DataManager;
import com.example.mealmonkeyfooddeleveryjava_admin.Data.Response.NetworkResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInPOST {

    private Application application;
    private FirebaseAuth Mauth;
    private MutableLiveData<NetworkResponse> data;
    public NetworkResponse networkResponse;

    public SignInPOST(Application application) {
        this.application = application;
        data = new MutableLiveData<>();
        Mauth = FirebaseAuth.getInstance();
        networkResponse = new NetworkResponse();
    }

    public LiveData<NetworkResponse> SignUpUser(String Email, String Password) {
        Mauth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        networkResponse.setCode(DataManager.SUCCESS_CODE);
                        data.setValue(networkResponse);
                    } else {
                        networkResponse.setCode(DataManager.ERROR_CODE);
                        data.setValue(networkResponse);
                        Toast.makeText(application, "Error login account", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> {
                    networkResponse.setCode(DataManager.ERROR_CODE);
                    data.setValue(networkResponse);
            Toast.makeText(application, "Error login account", Toast.LENGTH_SHORT).show();
        });

        return data;
    }
}
