package com.example.foodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Reserved_Display extends AppCompatActivity {
    Button cancelBtn,confirmBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_display);
        cancelBtn = findViewById(R.id.one_button);
        //confirmBtn = findViewById(R.id.confirm_button);

        String text1 = getIntent().getExtras().getString("FoodName");
        String text2 = getIntent().getExtras().getString("locations");
        String text3 = getIntent().getExtras().getString("Quantity");
        String text4 = getIntent().getExtras().getString("Description");



        TextView nameText1 = findViewById(R.id.confirm_name1);
        nameText1.setText(text1);
        TextView nameText2 = findViewById(R.id.confirm_location1);
        nameText2.setText(text2);
        TextView nameText3 = findViewById(R.id.confirm_amount1);
        nameText3.setText(text3);
        TextView nameText4 = findViewById(R.id.confirm_desc1);
        nameText4.setText(text4);

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
