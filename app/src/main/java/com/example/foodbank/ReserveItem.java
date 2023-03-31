package com.example.foodbank;

public class ReserveItem {

    private String FoodName;
    private String Description;
    private String Quantity;
    private String locations;

    public ReserveItem(String foodName, String description, String quantity, String locations) {
        this.FoodName = foodName;
        this.Description= description;
        this.Quantity = quantity;
        this.locations = locations;
    }

    public ReserveItem() {
    }

    public String getFoodName1() {

        return FoodName;
    }

    public String getDescription1() {

        return Description;
    }

    public String getQuantity1() {

        return Quantity;
    }

    public String getLocations1() {

        return locations;
    }


}
