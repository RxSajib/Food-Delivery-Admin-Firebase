package com.example.mealmonkeyadmin.UI.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.mealmonkeyadmin.Data.DataManager;
import com.example.mealmonkeyadmin.Network.NetworkViewModel.ViewModel;
import com.example.mealmonkeyadmin.R;
import com.example.mealmonkeyadmin.Recyclerview.Adapter.RestaurantsAdapter;
import com.example.mealmonkeyadmin.UI.Utils.Utils;
import com.example.mealmonkeyadmin.databinding.AddrestrountsBinding;
import com.example.mealmonkeyadmin.databinding.AddrestruntlayoutBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AddRestrounts extends AppCompatActivity {

    private AddrestrountsBinding binding;
    private final int PERMISSION_CODE = 100;
    private AddrestruntlayoutBinding dialoagbinding;
    private String DownloadImageUri;
    private RestaurantsAdapter adapter;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.addrestrounts);

        init_view();
    }

    private void init_view(){
        binding.BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Animatoo.animateSlideRight(AddRestrounts.this);
            }
        });
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        adapter = new RestaurantsAdapter();
        binding.RecyclerView.setHasFixedSize(true);
        binding.RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        binding.AddRestaurant.setOnClickListener(v -> {
            open_dialoag();
        });

        binding.BackIcon.setOnClickListener(v -> {
            finish();
            Animatoo.animateSlideRight(AddRestrounts.this);
        });

        get_restrountsinfo();
    }

    private void open_dialoag(){
        MaterialAlertDialogBuilder Mbuilder = new MaterialAlertDialogBuilder(AddRestrounts.this, R.style.PauseDialog);
        dialoagbinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.addrestruntlayout, null, false);
        Mbuilder.setView(dialoagbinding.getRoot());


        dialoagbinding.AddRestruntButton.setOnClickListener(v -> {
            String Title = dialoagbinding.TitleInput.getText().toString().trim();
            String Details = dialoagbinding.DetailsInput.getText().toString().trim();
            dialoagbinding.circularImageView.setOnClickListener(v1 -> {
                if(ExternalStoresPermission()){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, PERMISSION_CODE);

                }
            });

            if(Title.isEmpty()){
                Toast.makeText(getApplicationContext(), "Title require", Toast.LENGTH_SHORT).show();
            }else if(Details.isEmpty()){
                Toast.makeText(getApplicationContext(), "Details require", Toast.LENGTH_SHORT).show();
            }else {
                progressbar("Wait for uploading");
               // iosDialog.show();
                viewModel.AddRestaurantsPost(DownloadImageUri, Title, Details)
                        .observe(this, networkResponse -> {
                            if(networkResponse.getCode() == DataManager.SUCCESS_CODE){
                                networkResponse.setCode(DataManager.DEFAULT_CODE);
                              //  iosDialog.dismiss();
                                Utils.hideProgressDialog();
                                Toast.makeText(getApplicationContext(), "Upload Success", Toast.LENGTH_SHORT).show();
                            }else if(networkResponse.getCode() == DataManager.ERROR_CODE){
                                networkResponse.setCode(DataManager.DEFAULT_CODE);
                               // iosDialog.dismiss();
                                Utils.hideProgressDialog();
                            }
                        });
            }
        });

        AlertDialog alertDialog = Mbuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

    }

    private boolean ExternalStoresPermission(){
        if(ContextCompat.checkSelfPermission(AddRestrounts.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            return true;
        }else {
            ActivityCompat.requestPermissions(AddRestrounts.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE);
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PERMISSION_CODE && resultCode == RESULT_OK){
            progressbar("Wait for uploading");
          //  iosDialog.show();
            dialoagbinding.circularImageView.setImageURI(data.getData());
            viewModel.upload_restaurants_image(data.getData())
                    .observe(this, restaurantsImage -> {
                        if(restaurantsImage != null){
                            DownloadImageUri = restaurantsImage.getImageUri();
                          //  iosDialog.dismiss();
                            Utils.hideProgressDialog();
                        }
                    });
        }
    }

    private void progressbar(String details){
       /* iosDialog = new IOSDialog.Builder(AddRestrounts.this)
                .setTitle("loading")
                .setDimAmount(3)
                .setSpinnerDuration(120)
                .setMessageContentGravity(Gravity.END)
                .setCancelable(false)
                .setMessageContent(details)
                .build();*/
        Utils.showProgressDialog(this);
    }

    private void get_restrountsinfo(){
        viewModel.GetRestaurants().observe(this, restaurantModels -> {
            if(restaurantModels != null){
                binding.MessageText.setVisibility(View.GONE);
                binding.MessageIcon.setVisibility(View.GONE);
                adapter.setList(restaurantModels);
                binding.RecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                adapter.OnItmClick(Title -> {
                    goto_addfood(Title);
                });
            }else {
                binding.MessageIcon.setVisibility(View.VISIBLE);
                binding.MessageText.setVisibility(View.VISIBLE);
            }
        });
    }

    private void goto_addfood(String Title){
        Intent intent = new Intent(getApplicationContext(), AddFood.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(DataManager.Data, Title);
        startActivity(intent);
        Animatoo.animateSlideLeft(AddRestrounts.this);
    }
}