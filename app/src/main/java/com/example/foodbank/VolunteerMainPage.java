package com.example.foodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

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

import com.example.foodbank.databinding.ActivityVolunteerMainPageBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VolunteerMainPage extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    private DrawerLayout DrawerLayout;
    private NavigationView navigationView;
    Toolbar toolbar;
    //RAdapter RAdapter;
    VAdapter VAdapter;
    ArrayList<FoodItem> list1;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityVolunteerMainPageBinding binding;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVolunteerMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarVolunteerMainPage.toolbar);
        binding.appBarVolunteerMainPage.reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reserve = new Intent(VolunteerMainPage.this,Reservation.class);
                startActivity(reserve);
            }
        });

        //display reserve food

        swipeRefreshLayout =findViewById(R.id.swipe1);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                    overridePendingTransition(0, 0);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);

                VAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView=findViewById(R.id.recview2);
        database= FirebaseDatabase.getInstance().getReference("FoodReserve");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list1 = new ArrayList<>();
        VAdapter = new VAdapter(this,list1);
        recyclerView.setAdapter(VAdapter);



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FoodItem user = dataSnapshot.getValue(FoodItem.class);
                    //Toast.makeText(VolunteerMainPage.this, user.toString(), Toast.LENGTH_SHORT).show();
                    list1.add(user);

                }


                VAdapter.notifyDataSetChanged();

                //recreate();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        //Toast.makeText(this, "WELCOME", Toast.LENGTH_SHORT).show();





        /*DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_volunteer_main_page);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/



        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout = findViewById(R.id.drawer_layout);
        navigationView  = findViewById(R.id.nav_view3);
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

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            startActivity(new Intent(VolunteerMainPage.this, VolunteerMainPage.class));
                            break;

                        case R.id.nav_Map:
                            startActivity(new Intent(VolunteerMainPage.this, Map.class));
                            break;
                        case R.id.nav_LogOut:
                            startActivity(new Intent(VolunteerMainPage.this, HomePage.class));
                            finish();
                            break;
                    }

                    DrawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
            };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.volunteer_main_page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_volunteer_main_page);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}