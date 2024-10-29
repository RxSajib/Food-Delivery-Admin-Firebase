package com.example.mealmonkeyadmin.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.mealmonkeyadmin.Data.DataManager;
import com.example.mealmonkeyadmin.Data.Model.NavigationView;
import com.example.mealmonkeyadmin.Network.NetworkViewModel.ViewModel;
import com.example.mealmonkeyadmin.R;
import com.example.mealmonkeyadmin.UI.SignIn;
import com.example.mealmonkeyadmin.Recyclerview.Adapter.NavagationAdapter;
import com.example.mealmonkeyadmin.databinding.HomeBinding;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private HomeBinding binding;
    private ViewModel viewModel;
    private NavagationAdapter adapter;
    private List<NavigationView> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.home);

        init_view();
        set_naviteam();
    }

    private void init_view(){
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        binding.RecyclerView.setHasFixedSize(true);

        binding.MenuButton.setOnClickListener(view -> {
            binding.DrawerLayout.openDrawer(Gravity.LEFT);
        });

        binding.LogOutIcon.setOnClickListener(view -> {
            binding.DrawerLayout.closeDrawer(Gravity.LEFT);
        });

        binding.TotalOrder.setOnClickListener(view -> {
            var i = new Intent(getApplicationContext(), AllOrder.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.IsLoginAccount().observe(this, aBoolean -> {
            if(aBoolean){

            }else {
                goto_signin();
            }
        });
    }

    private void goto_signin(){
        Intent intent = new Intent(getApplicationContext(), SignIn.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        Animatoo.animateSlideLeft(Home.this);
    }

    private void set_naviteam(){
        NavigationView one  = new NavigationView(R.drawable.nav_dashboard, "DashBoard");
        NavigationView two  = new NavigationView(R.drawable.nav_home, "Add Home");
        NavigationView three  = new NavigationView(R.drawable.nav_banner, "Home Banner");
        NavigationView four  = new NavigationView(R.drawable.nav_foodorder, "Food Order");
        NavigationView five  = new NavigationView(R.drawable.nav_foodmenu, "Manage Food Menu");
        NavigationView six  = new NavigationView(R.drawable.nav_user, "Manage User");
        NavigationView seven  = new NavigationView(R.drawable.nav_location, "Manage City");
        NavigationView eight  = new NavigationView(R.drawable.nav_bell, "Notification");
        NavigationView nine  = new NavigationView(R.drawable.nav_feedback, "Feedback");
        NavigationView ten  = new NavigationView(R.drawable.nav_setting, "Setting");
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        list.add(six);
        list.add(seven);
        list.add(eight);
        list.add(nine);
        list.add(ten);

        adapter = new NavagationAdapter(list);
        binding.RecyclerView.setAdapter(adapter);

        adapter.SetOnclickLisiner(position -> {
            if(position == 0){
                binding.DrawerLayout.closeDrawer(Gravity.LEFT);
            }

            if(position == 4){
                binding.DrawerLayout.closeDrawer(Gravity.LEFT);
                goto_managefoodmenu();
            }
        });
    }

    private void goto_managefoodmenu(){
        Intent intent = new Intent(getApplicationContext(), AddRestrounts.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Animatoo.animateSlideLeft(Home.this);
    }
}