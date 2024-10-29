package com.example.mealmonkeyadmin.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.mealmonkeyadmin.R;
import com.example.mealmonkeyadmin.databinding.SplashscreenBinding;

public class SplashScreen extends AppCompatActivity {

    private SplashscreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.splashscreen);

        new Handler()
                .postDelayed(() -> goto_home(), 3000);
    }

    private void goto_home(){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        Animatoo.animateSlideLeft(SplashScreen.this);
    }
}