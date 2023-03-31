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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

import java.util.ArrayList;
import java.util.Calendar;

public class Reservation extends AppCompatActivity {

    private Spinner spinner;

    private ArrayList<String> arrayList = new ArrayList<>();
    //ArrayAdapter<String> adapter;


    private FirebaseDatabase database;
    //private DatabaseReference databaseReference;
    //private DatabaseReference mDataBase;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodbank-25692-default-rtdb.firebaseio.com/");
    private EditText location, FoodName, Description, Quantity;
    Button Reserve,CancelReserve;
    private TextView Date;
    private Object Spinner;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);


        database=FirebaseDatabase.getInstance();

        FoodName=(EditText)findViewById(R.id.reserve_name);
        //location=(EditText) findViewById(R.id.reserve_location);
        spinner = (Spinner) findViewById(R.id.spinner);
        Date = findViewById(R.id.date2);
        //showDataSpinner();


        Description=(EditText) findViewById(R.id.reserve_descript);
        Quantity=(EditText) findViewById(R.id.reserve_amount);
        Reserve = (Button)findViewById(R.id.reserve_button);
        CancelReserve = (Button)findViewById(R.id.reserve_cancel_button) ;


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Reservation.this, android.R.style.Theme_Holo_Dialog_MinWidth
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

        CancelReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reservation.this,VolunteerMainPage.class);
                startActivity(intent);


            }
        });

        Reserve.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String FoodName3 = FoodName.getText().toString().trim();
                //String location3 = location.getText().toString().trim();
                String spinner3 = spinner.getSelectedItem().toString();
                String description3 = Description.getText().toString().trim();
                String quantity3 = Quantity.getText().toString().trim();
                String date1 = Date.getText().toString().trim();

                /*if(FoodName3.isEmpty() || description3.isEmpty() || quantity3.isEmpty()){
                    Toast.makeText(Reservation.this,"enter details",Toast.LENGTH_LONG).show();

                }*/
                if(FoodName3.isEmpty()){
                    FoodName.setError("FoodName is EMPTY");
                    FoodName.requestFocus();
                }
                else if(description3.isEmpty()){
                    Description.setError("LastName is EMPTY");
                    Description.requestFocus();
                }

                else if(quantity3.isEmpty()){
                    Quantity.setError("Quantity is EMPTY");
                    Quantity.requestFocus();

                }
                else {

                    databaseReference.child("FoodReserve").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("FoodReserve").child(FoodName3).child("FoodName").setValue(FoodName3);
                            //databaseReference.child("FoodReserve").child(FoodName3).child("locations").setValue(location3);
                            databaseReference.child("FoodReserve").child(FoodName3).child("locations").setValue(spinner3);
                            databaseReference.child("FoodReserve").child(FoodName3).child("Description").setValue(description3);
                            databaseReference.child("FoodReserve").child(FoodName3).child("Quantity").setValue(quantity3);
                            databaseReference.child("FoodReserve").child(FoodName3).child("Date").setValue(date1);

                            Toast.makeText(Reservation.this,"Success",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });


    }

    private void showDataSpinner() {
    databaseReference.child("FoodReserve").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            arrayList.clear();
           // for(DataSnapshot item: DataSnapshot.getChildren());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

    }
}