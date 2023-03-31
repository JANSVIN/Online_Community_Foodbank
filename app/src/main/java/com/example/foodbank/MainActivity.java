package com.example.foodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




    public class MainActivity extends AppCompatActivity {

        ImageView imageView;
        TextView textView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            imageView = (ImageView) findViewById(R.id.imageview);
            textView = (TextView) findViewById(R.id.textView6);

            imageView.animate().alpha(0f).setDuration(0);
            textView.animate().alpha(0f).setDuration(0);

            imageView.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {


                    textView.animate().alpha(1f).setDuration(800);

                }
            });
            {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //HOMEPAGE
                        Intent intent = new Intent(MainActivity.this,HomePage.class);

                        //Intent intent = new Intent(MainActivity.this,VolunteerMainPage.class);
                        //Intent intent = new Intent(MainActivity.this, RestaurantMainPage.class);
                        //Intent intent = new Intent(MainActivity.this, PublicMainPage.class);
                        //Intent intent = new Intent(MainActivity.this,Admin_MainPage.class);
                        //Intent intent = new Intent(MainActivity.this,ManageFood.class);

                        startActivity(intent);

                       finish();
                    }
                }, 2000);
            }
        }
    }

