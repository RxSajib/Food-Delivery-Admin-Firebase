package com.example.mealmonkeyadmin.Data.Model;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

public class OrderItem {
    private String  FoodLocation, FoodMadeBy, FoodTitle, ImageOne, FoodPrice;

    @PrimaryKey(autoGenerate = false)
    private @NonNull long FoodID;
    private float Rating;
    private int TotalPrice;
    private int Quantity;
    private String Status;

    public OrderItem(String foodLocation, String foodMadeBy, String foodTitle, String imageOne, Long foodID, Integer quantity, float rating, String status, Integer totalPrice, String foodPrice){
        FoodLocation = foodLocation;
        FoodMadeBy = foodMadeBy;
        Rating = rating;
        Status = status;
        FoodTitle = foodTitle;
        ImageOne = imageOne;
        FoodID = foodID;
        TotalPrice = totalPrice;
        Quantity = quantity;
        FoodPrice = foodPrice;
    }


    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getFoodLocation() {
        return FoodLocation;
    }

    public void setFoodLocation(String foodLocation) {
        FoodLocation = foodLocation;
    }

    public String getFoodMadeBy() {
        return FoodMadeBy;
    }

    public void setFoodMadeBy(String foodMadeBy) {
        FoodMadeBy = foodMadeBy;
    }

    public String getFoodPrice() {
        return FoodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        FoodPrice = foodPrice;
    }

    public String getFoodTitle() {
        return FoodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        FoodTitle = foodTitle;
    }


    public String getImageOne() {
        return ImageOne;
    }

    public void setImageOne(String imageOne) {
        ImageOne = imageOne;
    }


    public long getFoodID() {
        return FoodID;
    }

    public void setFoodID(long foodID) {
        FoodID = foodID;
    }


    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }



    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
