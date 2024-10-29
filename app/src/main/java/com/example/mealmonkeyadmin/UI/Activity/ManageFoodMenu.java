package com.example.mealmonkeyadmin.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.mealmonkeyadmin.R;
import com.example.mealmonkeyadmin.databinding.ManagefoodmenuBinding;

public class ManageFoodMenu extends AppCompatActivity {

    private ManagefoodmenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.managefoodmenu);

        inti_view();
    }

    private void inti_view(){
        binding.BackIcon.setOnClickListener(view -> {
            finish();
            Animatoo.animateSlideRight(ManageFoodMenu.this);
        });

        binding.BackIcon.setOnClickListener(v -> {
            finish();
            Animatoo.animateSlideRight(ManageFoodMenu.this);
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Animatoo.animateSlideRight(ManageFoodMenu.this);
    }
}