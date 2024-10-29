package com.example.mealmonkeyadmin.Data.Model;

public class RestaurantModel {

    private String RestaurantDetails;
    private String RestaurantImageUri;
    private String RestaurantTitle;
    private long Timestamp;

    public RestaurantModel(){

    }

    public String getRestaurantDetails() {
        return RestaurantDetails;
    }

    public void setRestaurantDetails(String restaurantDetails) {
        RestaurantDetails = restaurantDetails;
    }

    public String getRestaurantImageUri() {
        return RestaurantImageUri;
    }

    public void setRestaurantImageUri(String restaurantImageUri) {
        RestaurantImageUri = restaurantImageUri;
    }

    public String getRestaurantTitle() {
        return RestaurantTitle;
    }

    public void setRestaurantTitle(String restaurantTitle) {
        RestaurantTitle = restaurantTitle;
    }

    public long getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(long timestamp) {
        Timestamp = timestamp;
    }
}
