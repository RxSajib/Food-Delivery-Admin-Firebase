package com.example.mealmonkeyfooddeleveryjava_admin.Network.NetworkViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mealmonkeyfooddeleveryjava_admin.Data.Response.NetworkResponse;
import com.example.mealmonkeyfooddeleveryjava_admin.Network.Repository.IsLoginGET;
import com.example.mealmonkeyfooddeleveryjava_admin.Network.Repository.SignInPOST;

public class ViewModel extends AndroidViewModel {

    private SignInPOST signInPOST;
    private IsLoginGET isLoginGET;

    public ViewModel(@NonNull Application application) {
        super(application);

        signInPOST = new SignInPOST(application);
        isLoginGET = new IsLoginGET(application);
    }

    public LiveData<NetworkResponse> SignInUser(String Email, String Password){
       return signInPOST.SignUpUser(Email, Password);
    }

    public LiveData<Boolean> IsLoginAccount(){
        return isLoginGET.IsLoginAccount();
    }
}
