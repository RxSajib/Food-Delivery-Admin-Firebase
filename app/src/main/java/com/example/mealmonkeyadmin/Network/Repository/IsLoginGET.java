package com.example.mealmonkeyadmin.Network.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IsLoginGET {

    private Application application;
    private MutableLiveData<Boolean> data;
    private FirebaseAuth Mauth;

    public IsLoginGET(Application application){
        this.application = application;
        data = new MutableLiveData<>();
        Mauth = FirebaseAuth.getInstance();
    }

    public LiveData<Boolean> IsLoginAccount(){
        FirebaseUser Muser = Mauth.getCurrentUser();
        if(Muser != null){
            data.setValue(true);
        }else {
            data.setValue(false);
        }

        return data;
    }
}
