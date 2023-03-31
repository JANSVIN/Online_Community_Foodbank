package com.example.foodbank;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Donation extends AppCompatActivity {

    private Spinner spinner;
    private FirebaseDatabase database;
    //private DatabaseReference databaseReference;
    //private DatabaseReference mDataBase;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodbank-25692-default-rtdb.firebaseio.com/");
    private EditText location, FoodName, Description, Quantity;
    private TextView Date;
    Button Donate,CancelDonate;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        database=FirebaseDatabase.getInstance();
        //databaseReference=database.getReference();
        //mDataBase=FirebaseDatabase.getInstance().getReference();

        spinner = (Spinner) findViewById(R.id.spinner1);
        FoodName=(EditText)findViewById(R.id.donate_name);
        //location=(EditText) findViewById(R.id.donate_location);
        Description=(EditText) findViewById(R.id.donate_descript);
        Quantity=(EditText) findViewById(R.id.donate_amount);
        Donate = (Button)findViewById(R.id.donate_button);
        CancelDonate = (Button)findViewById(R.id.donate_cancel_button) ;
        Date = findViewById(R.id.date);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Donation.this, android.R.style.Theme_Holo_Dialog_MinWidth
                        ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                datePickerDialog.show();
            }
        });
            setListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month+1;
                    String date1 = day+"/"+month+"/"+year;
                    Date.setText(date1);
                }
            };


        CancelDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent canceldonate = new Intent(Donation.this,RestaurantMainPage.class);
                startActivity(canceldonate);
            }
        });

        Donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FoodName1 = FoodName.getText().toString().trim();
                //String location1 = location.getText().toString().trim();
                String spinner1 = spinner.getSelectedItem().toString();
                String description1 = Description.getText().toString().trim();
                String quantity1 = Quantity.getText().toString().trim();
                String date1 = Date.getText().toString().trim();


              /*if(FoodName1.isEmpty() ||  description1.isEmpty() || quantity1.isEmpty()){
                    Toast.makeText(Donation.this,"enter details",Toast.LENGTH_LONG).show();

                }*/
                if(FoodName1.isEmpty()){
                    FoodName.setError("FoodName is EMPTY");
                    FoodName.requestFocus();
                }
                else if(description1.isEmpty()){
                    Description.setError("Description is EMPTY");
                    Description.requestFocus();
                }

                else if(quantity1.isEmpty()){
                    Quantity.setError("Quantity is EMPTY");
                    Quantity.requestFocus();

                }

                else {

                databaseReference.child("FoodItem").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("FoodItem").child(FoodName1).child("FoodName").setValue(FoodName1);
                        databaseReference.child("FoodItem").child(FoodName1).child("locations").setValue(spinner1);
                        databaseReference.child("FoodItem").child(FoodName1).child("Description").setValue(description1);
                        databaseReference.child("FoodItem").child(FoodName1).child("Quantity").setValue(quantity1);
                        databaseReference.child("FoodItem").child(FoodName1).child("Date").setValue(date1);

                        Toast.makeText(Donation.this,"Success",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                }
            }
        });

    }
}