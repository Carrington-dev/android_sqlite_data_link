package com.stemgon.datalink.home.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.stemgon.datalink.R;

public class HomeActivity extends AppCompatActivity {
    Button goToAddBtn;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        goToAddBtn = findViewById(R.id.goToAddBtn);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if( id == R.id.nav_home){
                    Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else if(id == R.id.nav_update){
                    Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, UpdateActivity.class);
                    startActivity(intent);
                }else if(id == R.id.nav_all_users){
                    Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, ListActivity.class);
                    startActivity(intent);

                }else if(id == R.id.nav_add){
                    Toast.makeText(HomeActivity.this, "Add user", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                    startActivity(intent);

                }else{

                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        goToAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}