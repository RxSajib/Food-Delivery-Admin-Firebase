package com.example.mealmonkeyadmin.Network.Repository;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anstrontechnologies.corehelper.AnstronCoreHelper;
import com.example.mealmonkeyadmin.Data.Model.RestaurantsImage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.iceteck.silicompressorr.FileUtils;
import com.iceteck.silicompressorr.SiliCompressor;

import java.io.File;

public class AddRestaurantsImagePOST {

    private Application application;
    private StorageReference RestaurantsRef;
    private MutableLiveData<RestaurantsImage> data;
    private AnstronCoreHelper anstronCoreHelper;
    private RestaurantsImage RestaurantsImage;

    public AddRestaurantsImagePOST(Application application) {
        this.application = application;
        RestaurantsRef = FirebaseStorage.getInstance().getReference();
        anstronCoreHelper = new AnstronCoreHelper(application);
        RestaurantsImage = new RestaurantsImage();
    }

    public LiveData<RestaurantsImage> UploadRestaurantsImage(Uri posterpath){
        data = new MutableLiveData<>();
        File file = new File(SiliCompressor.with(application)
        .compress(FileUtils.getPath(application, posterpath), new File(application.getFilesDir(), "temp")));

        Uri fromfile = Uri.fromFile(file);
        RestaurantsRef.child(anstronCoreHelper.getFileNameFromUri(fromfile))
                .putFile(fromfile)
                .addOnSuccessListener(taskSnapshot -> {
                    if(taskSnapshot.getMetadata() != null){
                        if(taskSnapshot.getMetadata().getReference() != null){
                            Task<Uri> result_task = taskSnapshot.getStorage().getDownloadUrl();
                            result_task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    RestaurantsImage.setImageUri(uri.toString());
                                    data.setValue(RestaurantsImage);
                                }
                            }).addOnFailureListener(e -> {
                                RestaurantsImage.setImageUri(null);
                                data.setValue(RestaurantsImage);
                            });
                        }
                    }
                }).addOnFailureListener(e -> {
                    RestaurantsImage.setImageUri(null);
                    data.setValue(RestaurantsImage);
                });
        return data;
    }

}
