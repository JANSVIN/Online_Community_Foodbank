package com.example.foodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText emailid, password;
    private Button login;
    private TextView registerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


       emailid = findViewById(R.id.TestEmail);
        password=findViewById(R.id.TestPass);
        login=findViewById(R.id.loginbtn);
        registerBtn=findViewById(R.id.createacc);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String pass = password.getText().toString();

                if(email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(Login.this ,"Please enter details",Toast.LENGTH_LONG).show();

                }


            }
        });
       registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,RegisterRestaurant.class));
            }
        });

    }
}