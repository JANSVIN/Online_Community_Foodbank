package com.example.foodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClaimedDisplay extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button cancelBtn,confirmBtn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claimed_display);
        cancelBtn = findViewById(R.id.cancel_button);
        //confirmBtn = findViewById(R.id.confirm_button);

        String text1 = getIntent().getExtras().getString("FoodName");
        String text2 = getIntent().getExtras().getString("locations");
        String text3 = getIntent().getExtras().getString("Quantity");
        String text5 = getIntent().getExtras().getString("Date");
        String text4 = getIntent().getExtras().getString("Description");



        TextView nameText1 = findViewById(R.id.confirm_name);
        nameText1.setText(text1);
        TextView nameText2 = findViewById(R.id.confirm_location);
        nameText2.setText(text2);
        TextView nameText3 = findViewById(R.id.confirm_amount);
        nameText3.setText(text3);
        TextView nameText4 = findViewById(R.id.confirm_desc);
        nameText4.setText(text4);
        TextView nameText5 = findViewById(R.id.confirm_date);
        nameText5.setText(text5);

       /*confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(ClaimedDisplay.this,PublicMainPage.class);
                startActivity(activity);
            }
        });*/

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent mIntent = new Intent(ClaimedDisplay.this,PublicMainPage.class);

                //startActivity(mIntent);
                finish();

            }
        });




    }
}