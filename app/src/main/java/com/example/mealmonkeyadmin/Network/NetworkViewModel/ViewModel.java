package com.example.mealmonkeyadmin.Network.NetworkViewModel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mealmonkeyadmin.Data.Model.OrderItem;
import com.example.mealmonkeyadmin.Data.Model.OrderModel;
import com.example.mealmonkeyadmin.Data.Model.RestaurantModel;
import com.example.mealmonkeyadmin.Data.Model.RestaurantsImage;
import com.example.mealmonkeyadmin.Data.Response.NetworkResponse;
import com.example.mealmonkeyadmin.Network.Repository.AddRestaurantsImagePOST;
import com.example.mealmonkeyadmin.Network.Repository.FoodImageStoragePOST;
import com.example.mealmonkeyadmin.Network.Repository.IsLoginGET;
import com.example.mealmonkeyadmin.Network.Repository.OrderDetailsGET;
import com.example.mealmonkeyadmin.Network.Repository.OrderFoodGET;
import com.example.mealmonkeyadmin.Network.Repository.RestaurantsGET;
import com.example.mealmonkeyadmin.Network.Repository.RestaurantsPOST;
import com.example.mealmonkeyadmin.Network.Repository.SignInPOST;
import com.example.mealmonkeyadmin.Network.Repository.UploadFoodPOST;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private OrderFoodGET orderFoodGET;
    private OrderDetailsGET orderDetailsGET;
    private SignInPOST signInPOST;
    private IsLoginGET isLoginGET;
    private AddRestaurantsImagePOST addRestaurantsImagePOST;
    private RestaurantsPOST restaurantsPOST;
    private RestaurantsGET restaurantsGET;
    private FoodImageStoragePOST foodImageStoragePOST;
    private UploadFoodPOST uploadFoodPOST;

    public ViewModel(@NonNull Application application) {
        super(application);

        orderFoodGET = new OrderFoodGET(application);
        orderDetailsGET = new OrderDetailsGET(application);
        signInPOST = new SignInPOST(application);
        isLoginGET = new IsLoginGET(application);
        addRestaurantsImagePOST = new AddRestaurantsImagePOST(application);
        restaurantsPOST = new RestaurantsPOST(application);
        restaurantsGET = new RestaurantsGET(application);
        foodImageStoragePOST = new FoodImageStoragePOST(application);
        uploadFoodPOST = new UploadFoodPOST(application);
    }

    public LiveData<List<OrderItem>> GetFoodOrderItem(String ID){
        return orderFoodGET.OrderItem(ID);
    }
    public LiveData<List<OrderModel>> GetOrder(){
        return orderDetailsGET.GetOrderDetails();
    }
    public LiveData<NetworkResponse> SignInUser(String Email, String Password){
       return signInPOST.SignUpUser(Email, Password);
    }

    public LiveData<Boolean> IsLoginAccount(){
        return isLoginGET.IsLoginAccount();
    }

    public LiveData<RestaurantsImage> upload_restaurants_image(Uri posterpath){
        return addRestaurantsImagePOST.UploadRestaurantsImage(posterpath);
    }

    public LiveData<NetworkResponse> AddRestaurantsPost(String ImageUri, String Title, String Details){
        return restaurantsPOST.UploadRestaurantsData(ImageUri, Title, Details);
    }

    public LiveData<List<RestaurantModel>> GetRestaurants(){
        return restaurantsGET.getRestaurants();
    }

    public LiveData<String> UploadFoodImage(Uri ImageUri){
        return foodImageStoragePOST.UploadFoodImage(ImageUri);
    }

    public LiveData<NetworkResponse> UploadFood(String Title, String FoodDetails, String FoodType, String FoodLocation, String RestaurantsLocation, String FoodMadeBy, String FoodPrice, String FoodOffer, String Promotion, String ImageOne, String ImageTwo, String ImageThree, String ImageFour, String RestaurantsName){
        return uploadFoodPOST.UploadFood(Title, FoodDetails, FoodType, FoodLocation, RestaurantsLocation, FoodMadeBy, FoodPrice, FoodOffer, Promotion,ImageOne, ImageTwo, ImageThree, ImageFour, RestaurantsName);
    }
}
