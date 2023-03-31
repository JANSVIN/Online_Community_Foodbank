package com.example.foodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choice extends AppCompatActivity {

    Button restaurant, public_user, volunteer;
    Intent intent;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        intent = getIntent();
        type = intent.getStringExtra("Home").trim();

        restaurant = findViewById(R.id.restaurant);
        public_user = findViewById(R.id.public_user);
        volunteer = findViewById(R.id.volunteer);

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Email")){
                    Intent loginemailres =  new Intent(Choice.this,LoginRestaurant.class);
                    startActivity(loginemailres);
                    //finish();
                }
                if(type.equals("Signup")){
                    Intent registerres =  new Intent(Choice.this,RegisterRestaurant.class);
                    startActivity(registerres);
                    //finish();
                }
            }
        });

        public_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Email")){
                    Intent loginemailpublic =  new Intent(Choice.this,LoginPublic.class);
                    startActivity(loginemailpublic);
                    //finish();
                }

                if(type.equals("Signup")){
                    Intent registerpublic =  new Intent(Choice.this,RegisterPublic.class);
                    startActivity(registerpublic);
                    //finish();
                }
            }
        });

        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.equals("Email")){
                    Intent loginemailvol =  new Intent(Choice.this,LoginVolunteer.class);
                    startActivity(loginemailvol);
                    //finish();
                }
                if(type.equals("Signup")){
                    Intent registervol =  new Intent(Choice.this,RegisterVolunteer.class);
                    startActivity(registervol);
                    //finish();
                }
            }
        });
    }
}