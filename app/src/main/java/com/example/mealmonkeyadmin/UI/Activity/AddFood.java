package com.example.mealmonkeyadmin.UI.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.mealmonkeyadmin.Data.DataManager;
import com.example.mealmonkeyadmin.Data.Model.FoodType;
import com.example.mealmonkeyadmin.Data.Response.NetworkResponse;
import com.example.mealmonkeyadmin.Network.NetworkViewModel.ViewModel;
import com.example.mealmonkeyadmin.R;
import com.example.mealmonkeyadmin.Recyclerview.Adapter.FoodCountryAdapter;
import com.example.mealmonkeyadmin.Recyclerview.Adapter.FoodTypeAdapter;
import com.example.mealmonkeyadmin.UI.Utils.Utils;
import com.example.mealmonkeyadmin.databinding.AddfoodBinding;
import com.example.mealmonkeyadmin.databinding.CountrylayoutBinding;
import com.example.mealmonkeyadmin.databinding.FoodtyplayoutBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddFood extends AppCompatActivity {

    private AddfoodBinding binding;
    private String Title;
    private static final int PERMISSIONCODE = 200;
    private String ImageUriOne = null;
    private String ImageUriTwo = null;
    private String ImageUriThree = null;
    private String ImageUriFour = null;
    private String FoodOffer = null;
    private String FoodPromotion = null;

    private List<FoodType> foodTypeList = new ArrayList<>();
    FoodType foodTypeone = new FoodType(DataManager.Food, R.drawable.ic_fastfoodblack);
    FoodType foodTypetwo = new FoodType(DataManager.Beverages, R.drawable.ic_drink);
    FoodType foodTypethree = new FoodType(DataManager.Desserts, R.drawable.ic_sweetblack);
    FoodType foodTypetfour = new FoodType(DataManager.Promotions, R.drawable.ic_promotionsblac);
    private FoodTypeAdapter foodTypeAdapter;

    private List<FoodType> countrylist = new ArrayList<>();
    FoodType foodTypecountryone = new FoodType(DataManager.Bangladesh, R.drawable.ic_bangladesh);
    FoodType foodTypecountrytwo = new FoodType(DataManager.India, R.drawable.ic_india);
    FoodType foodTypecountrythree = new FoodType(DataManager.Sri_Lankan, R.drawable.ic_srilanka);
    FoodType foodTypecountryfour = new FoodType(DataManager.Italian, R.drawable.ic_italian);
    private FoodCountryAdapter foodCountryAdapter;
    private ActivityResultLauncher<Intent> launcherone, launchertwo, launcherthree, launcherfour;

    private ViewModel viewModel;
    private String RestaurantsName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.addfood);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        RestaurantsName = getIntent().getStringExtra(DataManager.Data);

        init_view();
    }

    private void init_view() {
        uploadimageone();
        uploadimagetwo();
        uploadimagethree();
        uploadimagefour();

        Title = getIntent().getStringExtra(DataManager.Data);
        binding.ToolbarTitle.setText(Title);
        binding.BackIcon.setOnClickListener(v -> {
            finish();
            Animatoo.animateSlideRight(AddFood.this);
        });

        binding.FoodTypeBox.setOnClickListener(v -> {
            open_foodtypedialog();
        });

        binding.FoodLocationBox.setOnClickListener(v -> {
            open_locationdialog();
        });

        binding.ImageOne.setOnClickListener(v -> {
            if (ExternalStoresPermission()) {
                Intent intentpicone = new Intent(Intent.ACTION_PICK);
                intentpicone.setType("image/*");
                launcherone.launch(intentpicone);
            }
        });
        binding.ImageTwo.setOnClickListener(v ->{
            if(ExternalStoresPermission()){
                Intent intentpicone = new Intent(Intent.ACTION_PICK);
                intentpicone.setType("image/*");
                launchertwo.launch(intentpicone);
            }
        });
        binding.ImageThree.setOnClickListener(v ->{
            if (ExternalStoresPermission()) {
                Intent intentpicone = new Intent(Intent.ACTION_PICK);
                intentpicone.setType("image/*");
                launcherthree.launch(intentpicone);
            }
        });
        binding.ImageFour.setOnClickListener(v ->{
            if(ExternalStoresPermission()){
                Intent intentpicone = new Intent(Intent.ACTION_PICK);
                intentpicone.setType("image/*");
                launcherfour.launch(intentpicone);
            }
        });
        binding.OfferChaBox.setOnClickListener(v -> {
            if(binding.OfferChaBox.isChecked()){
                FoodOffer = DataManager.Offer;
            }else {
                FoodOffer = null;
            }
        });
        binding.PromotionBox.setOnClickListener(v -> {
            if(binding.PromotionBox.isChecked()){
                FoodPromotion = DataManager.Promotion;
            }else {
                FoodPromotion = null;
            }
        });

        binding.UpdateButtonID.setOnClickListener(v -> {
            uploadpost();
        });
    }

    private void open_locationdialog() {
        MaterialAlertDialogBuilder Mbuilder = new MaterialAlertDialogBuilder(AddFood.this, R.style.PauseDialog);
        CountrylayoutBinding Mbinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.countrylayout, null, false);
        Mbuilder.setView(Mbinding.getRoot());

        AlertDialog alertDialog = Mbuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        countrylist.clear();
        countrylist.add(foodTypecountryone);
        countrylist.add(foodTypecountrytwo);
        countrylist.add(foodTypecountrythree);
        countrylist.add(foodTypecountryfour);
        foodCountryAdapter = new FoodCountryAdapter(countrylist);
        Mbinding.CountryRecyclerView.setAdapter(foodCountryAdapter);
        foodCountryAdapter.SetOnClickLisiner(Text -> {
            alertDialog.dismiss();
            binding.CountryIcon.setVisibility(View.VISIBLE);
            if (Text.equals(DataManager.Bangladesh)) {
                binding.CountryIcon.setImageResource(R.drawable.ic_bangladesh);
                binding.FoodLocationText.setText(DataManager.Bangladesh);
            }
            if (Text.equals(DataManager.India)) {
                binding.CountryIcon.setImageResource(R.drawable.ic_india);
                binding.FoodLocationText.setText(DataManager.India);
            }
            if (Text.equals(DataManager.Sri_Lankan)) {
                binding.CountryIcon.setImageResource(R.drawable.ic_srilanka);
                binding.FoodLocationText.setText(DataManager.Sri_Lankan);
            }
            if (Text.equals(DataManager.Italian)) {
                binding.CountryIcon.setImageResource(R.drawable.ic_italian);
                binding.FoodLocationText.setText(DataManager.Italian);
            }
        });
    }

    private void open_foodtypedialog() {
        foodTypeList.clear();
        foodTypeList.add(foodTypeone);
        foodTypeList.add(foodTypetwo);
        foodTypeList.add(foodTypethree);
        foodTypeList.add(foodTypetfour);
        MaterialAlertDialogBuilder Mbuilder = new MaterialAlertDialogBuilder(AddFood.this, R.style.PauseDialog);
        FoodtyplayoutBinding Mbinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.foodtyplayout, null, false);
        Mbuilder.setView(Mbinding.getRoot());

        AlertDialog alertDialog = Mbuilder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        foodTypeAdapter = new FoodTypeAdapter(foodTypeList);
        Mbinding.FoodTypeRecyclerView.setAdapter(foodTypeAdapter);

        foodTypeAdapter.SetOnClickLisiner(Type -> {
            binding.FoodTypeIcon.setVisibility(View.VISIBLE);
            alertDialog.dismiss();
            if (Type.equals(DataManager.Food)) {
                binding.FoodTypeIcon.setImageResource(R.drawable.ic_fastfoodblack);
                binding.FoodTypeText.setText(DataManager.Food);
            }
            if (Type.equals(DataManager.Beverages)) {
                binding.FoodTypeIcon.setImageResource(R.drawable.ic_drink);
                binding.FoodTypeText.setText(DataManager.Beverages);
            }
            if (Type.equals(DataManager.Desserts)) {
                binding.FoodTypeIcon.setImageResource(R.drawable.ic_sweetblack);
                binding.FoodTypeText.setText(DataManager.Desserts);
            }
            if (Type.equals(DataManager.Promotions)) {
                binding.FoodTypeIcon.setImageResource(R.drawable.ic_promotionsblac);
                binding.FoodTypeText.setText(DataManager.Promotions);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        Animatoo.animateSlideRight(AddFood.this);
    }

    public boolean ExternalStoresPermission() {
        if (ContextCompat.checkSelfPermission(AddFood.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(AddFood.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONCODE);
            return false;
        }
    }

    private void uploadpost() {
        String FoodTitle = binding.FoodTitleInput.getText().toString().trim();
        String FoodDetails = binding.FoodDetailsInput.getText().toString().trim();
        String FoodType = binding.FoodTypeText.getText().toString().trim();
        String FoodLocation = binding.FoodLocationText.getText().toString().trim();
        String RestaurantsLocation = binding.RestrountsLocationInput.getText().toString().trim();
        String FoodMadeBy = binding.FoodMadeByInput.getText().toString().trim();
        String FoodPrice = binding.FoodPriceInput.getText().toString().trim();

        if (FoodTitle.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Food title require", Toast.LENGTH_SHORT).show();
        } else if (FoodDetails.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Food details require", Toast.LENGTH_SHORT).show();
        } else if (FoodType.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Food type require", Toast.LENGTH_SHORT).show();
        } else if (FoodLocation.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Food Location require", Toast.LENGTH_SHORT).show();
        } else if (RestaurantsLocation.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Restaurants Location require", Toast.LENGTH_SHORT).show();
        } else if (FoodMadeBy.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Food Made By require", Toast.LENGTH_SHORT).show();
        } else if (FoodPrice.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Food Price require", Toast.LENGTH_SHORT).show();
        }else if(ImageUriOne == null){
            Toast.makeText(getApplicationContext(), "Must include first image box", Toast.LENGTH_SHORT).show();
        }
        else {
            IsUploadingImageProgressbar("wait for uploading");
           // iosDialog.show();
            viewModel.UploadFood(FoodTitle, FoodDetails, FoodType, FoodLocation, RestaurantsLocation, FoodMadeBy, FoodPrice, FoodOffer, FoodPromotion, ImageUriOne, ImageUriTwo, ImageUriThree, ImageUriFour, RestaurantsName)
                    .observe(this, new Observer<NetworkResponse>() {
                        @Override
                        public void onChanged(NetworkResponse networkResponse) {
                            if(networkResponse.getCode() == DataManager.SUCCESS_CODE){
                                //iosDialog.dismiss();
                                Utils.hideProgressDialog();
                                finish();
                                Animatoo.animateSlideRight(AddFood.this);
                                Toast.makeText(getApplicationContext(), "Food add success", Toast.LENGTH_SHORT).show();
                            }else if(networkResponse.getCode() == DataManager.ERROR_CODE){
                              //  iosDialog.dismiss();
                                Utils.hideProgressDialog();
                            }
                        }
                    });
        }



    }

    private void uploadimageone() {
        launcherone = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            binding.ImageOne.setImageURI(result.getData().getData());
                            uplodafoodimageone(result.getData().getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    private void uploadimagetwo() {
        launchertwo = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            binding.ImageTwo.setImageURI(result.getData().getData());
                            uplodafoodimagetwo(result.getData().getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
    private void uploadimagethree() {
        launcherthree = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            binding.ImageThree.setImageURI(result.getData().getData());
                            uplodafoodimagethree(result.getData().getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    } private void uploadimagefour() {
        launcherfour = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            binding.ImageFour.setImageURI(result.getData().getData());
                            uplodafoodimagefour(result.getData().getData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    private void uplodafoodimageone(Uri ImageUri) {
        IsUploadingImageProgressbar("wait for uploading image");
        //iosDialog.show();
        viewModel.UploadFoodImage(ImageUri).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    ImageUriOne = s;
                    Utils.hideProgressDialog();
                  //  iosDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Upload success", Toast.LENGTH_SHORT).show();
                } else {
                    Utils.hideProgressDialog();
                 //   iosDialog.dismiss();
                }
            }
        });
    }
    private void uplodafoodimagetwo(Uri ImageUri) {
        IsUploadingImageProgressbar("wait for uploading image");
       // iosDialog.show();
        viewModel.UploadFoodImage(ImageUri).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    ImageUriTwo = s;
                    Utils.hideProgressDialog();
//                    iosDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Upload success", Toast.LENGTH_SHORT).show();
                } else {
                    Utils.hideProgressDialog();
                   // iosDialog.dismiss();
                }
            }
        });
    } private void uplodafoodimagethree(Uri ImageUri) {
        IsUploadingImageProgressbar("wait for uploading image");
        viewModel.UploadFoodImage(ImageUri).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    ImageUriThree = s;
                    Utils.hideProgressDialog();
                  //  iosDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Upload success", Toast.LENGTH_SHORT).show();
                } else {
                    Utils.hideProgressDialog();
               //     iosDialog.dismiss();
                }
            }
        });
    }private void uplodafoodimagefour(Uri ImageUri) {
        IsUploadingImageProgressbar("wait for uploading image");
        viewModel.UploadFoodImage(ImageUri).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    ImageUriFour = s;
                    //iosDialog.dismiss();
                    Utils.hideProgressDialog();
                    Toast.makeText(getApplicationContext(), "Upload success", Toast.LENGTH_SHORT).show();
                } else {
                    Utils.hideProgressDialog();
                   // iosDialog.dismiss();
                }
            }
        });
    }

    private void IsUploadingImageProgressbar(String Title) {
        /*iosDialog = new IOSDialog.Builder(AddFood.this)
                .setTitle("loading")
                .setDimAmount(3)
                .setSpinnerDuration(120)
                .setMessageContentGravity(Gravity.END)
                .setCancelable(false)
                .setMessageContent(Title)
                .build();*/
        Utils.showProgressDialog(this);
    }
}