package com.example.foodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_MainPage extends AppCompatActivity {
    Button restaurant, public_user, volunteer,logout;
    Intent intent;
    //String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_page);

        intent = getIntent();
        //type = intent.getStringExtra("Home").trim();

        restaurant = findViewById(R.id.restaurant1);
        public_user = findViewById(R.id.public_user1);
        volunteer = findViewById(R.id.volunteer1);
        logout = findViewById(R.id.logout);

        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent loginemailres =  new Intent(Admin_MainPage.this,Res_Display.class);
                    startActivity(loginemailres);
                    //finish();
            }
        });

        public_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent loginemailpublic =  new Intent(Admin_MainPage.this,User_Display.class);
                    startActivity(loginemailpublic);
                    //finish();
                }



        });



        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent loginemailvol =  new Intent(Admin_MainPage.this,Vol_Display.class);
                    startActivity(loginemailvol);
                    //finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginemailvol =  new Intent(Admin_MainPage.this,HomePage.class);
                startActivity(loginemailvol);
                finish();
            }
        });

    }
}