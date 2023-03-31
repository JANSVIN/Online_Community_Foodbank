package com.example.foodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button signinemail,signup,admin;
        //ImageView bgimage;

        signinemail= findViewById(R.id.signin);
        signup= findViewById(R.id.signup);
        admin = findViewById(R.id.admin);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(HomePage.this,LoginAdmin.class);
                startActivity(intent);

            }
        });

        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signemail = new Intent(HomePage.this,Choice.class);
                signemail.putExtra("Home","Email");
                startActivity(signemail);
                //finish();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(HomePage.this,Choice.class);
                signup.putExtra("Home","Signup");

                startActivity(signup);
                //finish();
            }
        });
    }
}