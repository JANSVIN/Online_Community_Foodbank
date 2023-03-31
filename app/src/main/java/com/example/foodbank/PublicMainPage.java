package com.example.foodbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SearchView;
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

import com.example.foodbank.databinding.ActivityPublicMainPageBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PublicMainPage extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    FAdapter FAdapter;
    ArrayList<FoodItem> list;
    Toolbar toolbar;
    private DrawerLayout DrawerLayout;
    private NavigationView navigationView;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityPublicMainPageBinding binding;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPublicMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarPublicMainPage.toolbar);
        binding.appBarPublicMainPage.map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(PublicMainPage.this,Map.class);
                startActivity(map);
            }
        });
        //original file
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        //navigation bar
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_Map, R.id.nav_Refresh)

                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_public_main_page);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout = findViewById(R.id.drawer_layout);
        navigationView  = findViewById(R.id.nav_view);
        navigationView.bringToFront();
       ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, DrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        DrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
       navigationView.setNavigationItemSelectedListener(sidenavListener);


        //swipe to refresh
        swipeRefreshLayout =findViewById(R.id.swipe1);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                overridePendingTransition(0, 0);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);

                FAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView=findViewById(R.id.recview1);
        database= FirebaseDatabase.getInstance().getReference("FoodItem");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        FAdapter = new FAdapter(this,list);
        recyclerView.setAdapter(FAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FoodItem user = dataSnapshot.getValue(FoodItem.class);

                    list.add(user);
                }

                FAdapter.notifyDataSetChanged();
                //Toast.makeText(PublicMainPage.this, "WELCOME", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private final NavigationView.OnNavigationItemSelectedListener sidenavListener=
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            startActivity(new Intent(PublicMainPage.this, PublicMainPage.class));
                            break;

                        case R.id.nav_Map:
                            startActivity(new Intent(PublicMainPage.this, Map.class));
                            break;
                        case R.id.nav_LogOut:
                            startActivity(new Intent(PublicMainPage.this, HomePage.class));
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
        getMenuInflater().inflate(R.menu.public_main_page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
      //recycler link to description
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_public_main_page);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();


    }
}



