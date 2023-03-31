package com.example.foodbank;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Food {
   /* private String FoodName;
    private String Quantity;
    private String Description;
    private String Locations;


    public Food (String FoodName, String Quantity, String Description, String Locations) {
        this.FoodName = FoodName;
        this.Quantity = Quantity;
        this.Description = Description;
        this.Locations = Locations;*/

    }

   /* public Food () {
        // empty constructor for firebase
    }

    public String getID() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("FoodItem");
        String foodname = this.FoodName;
        String location = this.Locations    ;
        final String[] id = new String[1];

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    if (item.child("name").equals(foodname) && item.child("name").equals(location))
                        id[0] = item.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return id[0];
    }

    public String getFoodName() {

        return this.FoodName;
    }

    public String getQuantity() {

        return this.Quantity;
    }

    public String getDescription() {

        return this.Description;
    }



    public String getLocations() {

        return this.Locations;
    }

}*/
