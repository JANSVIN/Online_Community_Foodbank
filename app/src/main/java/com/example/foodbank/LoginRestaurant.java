package com.example.foodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.AuthResult;

public class LoginRestaurant extends AppCompatActivity {
    private EditText emailid, password;
    private FirebaseAuth mAuth;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodbank-25692-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_restaurant);

        mAuth=FirebaseAuth.getInstance();


        emailid = findViewById(R.id.LoginEmail1);
        password=findViewById(R.id.LoginPass1);
        Button login = findViewById(R.id.login);
        TextView registerBtn = findViewById(R.id.createacc);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        //aunthericateUser();

                {
                    String email = emailid.getText().toString();
                    String pass = password.getText().toString();

                   /* if (email.isEmpty() || pass.isEmpty()) {
                        Toast.makeText(LoginRestaurant.this, "ENTER ALL DETAILS", Toast.LENGTH_LONG).show();

                    } */

                    if(email.isEmpty()){
                        emailid.setError("User ID is EMPTY");
                        emailid.requestFocus();

                    }
                    else if(pass.isEmpty()) {
                        password.setError("Password is EMPTY");
                        password.requestFocus();
                    }
                    else {
                        databaseReference.child("restaurants").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                //data exist?
                                if (snapshot.hasChild(email)) {
                                    //get data from database
                                    final String getPass = snapshot.child(email).child("Password").getValue(String.class);
                                    if (getPass.equals(pass)) {
                                        Toast.makeText(LoginRestaurant.this, "Successfully Login", Toast.LENGTH_LONG).show();
                                        //open restaurant mainpage
                                        startActivity(new Intent(LoginRestaurant.this, RestaurantMainPage.class));
                                        finish();
                                    } else {
                                        Toast.makeText(LoginRestaurant.this, "Wrong Info", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(LoginRestaurant.this, "Wrong Info", Toast.LENGTH_LONG).show();
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

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginRestaurant.this,RegisterRestaurant.class));
            }
        });
    }

    /*private void aunthericateUser() {

        String email = emailid.getText().toString();
        String pass = password.getText().toString();

        if(email.isEmpty() || pass.isEmpty()){
            Toast.makeText(LoginRestaurant.this ,"Please enter details",Toast.LENGTH_LONG).show();return;
        }
        else {

            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                            showRestaurantMain();
                            } else {
                                Toast.makeText(LoginRestaurant.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        }*/

    /*private void showRestaurantMain() {

        Intent intent =new Intent(this,RestaurantMainPage.class);
        startActivity(intent);





    }*/
}
