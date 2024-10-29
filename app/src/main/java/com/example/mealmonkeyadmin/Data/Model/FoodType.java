package com.example.mealmonkeyadmin.Data.Model;

public class FoodType {

    private String Title;
    private int Icon;

    public FoodType(String title, int icon) {
        Title = title;
        Icon = icon;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }
}
