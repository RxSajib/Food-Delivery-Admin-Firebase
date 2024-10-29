package com.example.mealmonkeyfooddeleveryjava_admin.Data.Model;

public class NavigationView {

    public int Icon;
    public String Title;

    public NavigationView(int icon, String title) {
        Icon = icon;
        Title = title;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
