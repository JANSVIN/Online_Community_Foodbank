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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.Objects;

public class RegisterVolunteer extends AppCompatActivity {

    EditText firstName, lastName, Temail, Tpassword;
    Button signup,login;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodbank-25692-default-rtdb.firebaseio.com/");
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_volunteer);
        mAuth=FirebaseAuth.getInstance();

        firstName = findViewById(R.id.testFirstName2);
        lastName = findViewById(R.id.testLastName2);
        Temail = findViewById(R.id.testEmail2);
        Tpassword = findViewById(R.id.testPass2);
        signup =(Button)findViewById(R.id.signup3);
        login = (Button) findViewById(R.id.button4);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openLogin();
                Intent login = new Intent(RegisterVolunteer.this,LoginVolunteer.class);
                //startActivity(new Intent(RegisterRestaurant.this,Login.class));
                startActivity(login);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get data from edittext
                String Fname = firstName.getText().toString();
                String Lname = lastName.getText().toString();
                String email = Temail.getText().toString();
                String pass = Tpassword.getText().toString();




                /*if(Fname.isEmpty() || Lname.isEmpty() || email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(RegisterVolunteer.this,"enter details",Toast.LENGTH_LONG).show();

                }*/

                if(Fname.isEmpty()){
                    firstName.setError("FirstName is EMPTY");
                    firstName.requestFocus();
                }
                else if(Lname.isEmpty()){
                    lastName.setError("LastName is EMPTY");
                    lastName.requestFocus();
                }

                else if(email.isEmpty()){
                    Temail.setError("User ID is EMPTY");
                    Temail.requestFocus();

                }
                else if(pass.isEmpty()) {
                    Tpassword.setError("Password is EMPTY");
                    Tpassword.requestFocus();
                }

                else{

                    /*mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(RegisterVolunteer.this, "Success", Toast.LENGTH_LONG).show();
                                    if(task.isSuccessful()){
                                        Users user = new Users(Fname,Lname,email);
                                        FirebaseDatabase.getInstance().getReference("volunteers")
                                                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                registerUser();
                                            }
                                        });
                                        //Toast.makeText(RegisterRestaurant.this, "Here mou???????", Toast.LENGTH_LONG).show();

                                    }
                                    else {
                                        Toast.makeText(RegisterVolunteer.this, "fail", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });*/

                    databaseReference.child("volunteers").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(email)){
                                Toast.makeText(RegisterVolunteer.this,"User is used",Toast.LENGTH_LONG).show();
                            }
                            else{
                                databaseReference.child("volunteers").child(email).child("Fname").setValue(Fname);
                                databaseReference.child("volunteers").child(email).child("Lname").setValue(Lname);
                                databaseReference.child("volunteers").child(email).child("Email").setValue(email);
                                databaseReference.child("volunteers").child(email).child("Password").setValue(pass);

                                Toast.makeText(RegisterVolunteer.this,"Success",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterVolunteer.this,HomePage.class));
                            }

                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });

    }

    private void registerUser() {

        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
        finish();
    }

}
