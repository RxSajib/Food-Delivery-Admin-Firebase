package com.example.mealmonkeyadmin.Network.Repository;

import android.app.Application;
import android.media.Image;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.anstrontechnologies.corehelper.AnstronCoreHelper;
import com.example.mealmonkeyadmin.Data.DataManager;
import com.example.mealmonkeyadmin.Data.Response.NetworkResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iceteck.silicompressorr.FileUtils;
import com.iceteck.silicompressorr.SiliCompressor;

import java.io.File;

public class FoodImageStoragePOST {

    private Application application;
    private MutableLiveData<String> data;
    private StorageReference MFoodImageStorage;
    private AnstronCoreHelper anstronCoreHelper;

    public FoodImageStoragePOST(Application application){
        this.application = application;
        MFoodImageStorage = FirebaseStorage.getInstance().getReference().child(DataManager.Food);
        anstronCoreHelper = new AnstronCoreHelper(application);
    }

    public LiveData<String> UploadFoodImage(Uri ImageUri){
        data = new MutableLiveData<>();
        File file = new File(new SiliCompressor(application).compress(FileUtils.getPath(application, ImageUri
        ), new File(application.getFilesDir(), "temp")));

        Uri fromfile = Uri.fromFile(file);
        MFoodImageStorage.child(anstronCoreHelper.getFileNameFromUri(fromfile))
                .putFile(fromfile)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        if(taskSnapshot.getMetadata() != null){
                            if(taskSnapshot.getMetadata().getReference() != null){
                                Task<Uri> result_task = taskSnapshot.getStorage().getDownloadUrl();
                                result_task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        if(uri != null){
                                            data.setValue(uri.toString());
                                        }
                                    }
                                });
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                data.setValue(null);
                Toast.makeText(application, "Unable to upload image "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return data;
    }
}
