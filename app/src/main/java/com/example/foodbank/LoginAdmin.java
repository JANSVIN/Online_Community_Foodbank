package com.example.foodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAdmin extends AppCompatActivity {
    private EditText emailid, password;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodbank-25692-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);


        mAuth= FirebaseAuth.getInstance();


        emailid = findViewById(R.id.LoginEmail3);
        password=findViewById(R.id.LoginPass3);
        Button login = findViewById(R.id.login3);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //aunthericateUser();



                {
                    String email = emailid.getText().toString();
                    String pass = password.getText().toString();

                   /* if( email.isEmpty() || pass.isEmpty())

                    {
                        Toast.makeText(LoginAdmin.this,"ENTER ALL DETAILS",Toast.LENGTH_LONG).show();

                    }*/

                   if(email.isEmpty()){
                        emailid.setError("User ID is EMPTY");
                        emailid.requestFocus();

                    }
                    else if(pass.isEmpty()) {
                        password.setError("Password is EMPTY");
                        password.requestFocus();
                    }

                    else {
                        databaseReference.child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //data exist?
                                if (snapshot.hasChild(email)) {
                                    //get data from database
                                    final String getPass = snapshot.child(email).child("Password").getValue(String.class);
                                    if (getPass.equals(pass)) {
                                        Toast.makeText(LoginAdmin.this, "Successfully Login", Toast.LENGTH_LONG).show();

                                        startActivity(new Intent(LoginAdmin.this, Admin_MainPage.class));
                                        finish();
                                    } else {
                                        Toast.makeText(LoginAdmin.this, "Wrong Info", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(LoginAdmin.this, "Wrong Info", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }
        });


    }
}