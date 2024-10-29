package com.example.mealmonkeyfooddeleveryjava_admin.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.mealmonkeyadmin.UI.Utils.Utils;
import com.example.mealmonkeyfooddeleveryjava_admin.Data.DataManager;
import com.example.mealmonkeyfooddeleveryjava_admin.Network.NetworkViewModel.ViewModel;
import com.example.mealmonkeyadmin.R;
import com.example.mealmonkeyfooddeleveryjava_admin.UI.Activity.Home;
import com.example.mealmonkeyadmin.databinding.SigninBinding;

public class SignIn extends AppCompatActivity {

    private SigninBinding signinBinding;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signinBinding = DataBindingUtil.setContentView(this, R.layout.signin);

        init_view();
        loginaccount();
    }

    private void init_view(){
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
    }

    private void loginaccount(){

        signinBinding.LoginButton.setOnClickListener(view -> {
            String Email = signinBinding.EmailInput.getText().toString().trim();
            String Password = signinBinding.PasswordInput.getText().toString().trim();

            if(Email.isEmpty()){
                Toast.makeText(getApplicationContext(), "Email require", Toast.LENGTH_SHORT).show();
            }else if(Password.isEmpty()){
                Toast.makeText(getApplicationContext(), "Password require", Toast.LENGTH_SHORT).show();
            }
            else {
                progressdialoag();
           //     iosDialog.show();
                viewModel.SignInUser(Email, Password)
                        .observe(this, networkResponse -> {
                            if(networkResponse.getCode() == DataManager.SUCCESS_CODE){
                                networkResponse.setCode(DataManager.DEFAULT_CODE);
                                //iosDialog.dismiss();
                                Utils.hideProgressDialog();
                                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_LONG).show();
                                goto_home();
                            }else {
                                networkResponse.setCode(DataManager.DEFAULT_CODE);
                             //   iosDialog.dismiss();
                                Utils.hideProgressDialog();
                            }
                        });
            }
        });

    }

    private void progressdialoag(){
        /*iosDialog = new IOSDialog.Builder(SignIn.this)
                .setTitle("loading")
                .setDimAmount(3)
                .setSpinnerDuration(120)
                .setMessageContentGravity(Gravity.END)
                .setCancelable(false)
                .setMessageContent("Login your Account")
                .build();*/
        Utils.showProgressDialog(this);
    }

    private void goto_home(){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        Animatoo.animateSlideLeft(SignIn.this);
    }
}