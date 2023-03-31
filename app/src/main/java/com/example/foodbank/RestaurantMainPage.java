package com.example.foodbank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.widget.Filter;
import android.widget.Filterable;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.foodbank.databinding.ActivityRestaurantMainPageBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RestaurantMainPage extends AppCompatActivity implements Filterable{

    RecyclerView recyclerView;
    DatabaseReference database;
    RAdapter RAdapter;
    ArrayList<FoodItem> list1;
    private ArrayList<FoodItem> FilterList;

    Toolbar toolbar;
    private DrawerLayout DrawerLayout;
    private NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityRestaurantMainPageBinding binding;
    SwipeRefreshLayout swipeRefreshLayout;
    //@Override
    //private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        binding = ActivityRestaurantMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarRestaurantMainPage.toolbar);
        binding.appBarRestaurantMainPage.donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent donate = new Intent(RestaurantMainPage.this,Donation.class);
            startActivity(donate);
            }
        });

        binding.appBarRestaurantMainPage.notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notify = new Intent(RestaurantMainPage.this,Notifications.class);
                startActivity(notify);
            }
        });
        binding.appBarRestaurantMainPage.managefood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent food = new Intent(RestaurantMainPage.this,ManageFood.class);
                startActivity(food);
            }
        });

        swipeRefreshLayout =findViewById(R.id.swipe5);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                overridePendingTransition(0, 0);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);

                RAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView=findViewById(R.id.recview3);
        database= FirebaseDatabase.getInstance().getReference("FoodReserve");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list1 = new ArrayList<>();
        RAdapter = new RAdapter(this,list1);
        recyclerView.setAdapter(RAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FoodItem user = dataSnapshot.getValue(FoodItem.class);

                    list1.add(user);

                }

                RAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });



        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout = findViewById(R.id.drawer_layout);
        navigationView  = findViewById(R.id.nav_view2);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, DrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        DrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(sidenavListener1);

    }
    private final NavigationView.OnNavigationItemSelectedListener sidenavListener1=
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //Toast.makeText(RestaurantMainPage.this, "???", Toast.LENGTH_SHORT).show();
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            startActivity(new Intent(RestaurantMainPage.this, RestaurantMainPage.class));
                            break;

                        case R.id.nav_Map:
                            startActivity(new Intent(RestaurantMainPage.this, Map.class));
                            break;
                        case R.id.nav_LogOut:

                            startActivity(new Intent(RestaurantMainPage.this, HomePage.class));
                            finish();
                            break;
                    }

                    DrawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
            };

   public void onBackPressed() {
        if (DrawerLayout.isDrawerOpen(GravityCompat.START)) {
            DrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.restaurant_main_page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_restaurant_main_page);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();




    }

    @Override

    public Filter getFilter() {
        return null;
    }


    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

        @Override
        public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
    }


    }


