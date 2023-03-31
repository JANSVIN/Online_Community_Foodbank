package com.example.foodbank;

public class FoodItem {
    //String FoodName,Description,Quantity, locations;
    private String FoodName;
    private String Description;
    private String Quantity;
    private String locations;
    private String Date;

    public FoodItem(String foodName, String description, String quantity, String locations,String date) {
        this.FoodName = foodName;
        this.Description = description;
        this.Quantity = quantity;
        this.locations = locations;
        this.Date = date;
    }

    public FoodItem() {
    }

    public String getFoodName() {
        return FoodName;
    }

    public String getDescription() {
        return Description;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getLocations() {
        return locations;
    }

    public String getDate() {
        return Date;
    }
}
