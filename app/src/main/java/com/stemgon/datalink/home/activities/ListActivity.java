package com.stemgon.datalink.home.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.stemgon.datalink.R;
import com.stemgon.datalink.home.adapters.UserAdapter;
import com.stemgon.datalink.home.database.helpers.DatabaseHelper;
import com.stemgon.datalink.home.listeners.UserClickLister;
import com.stemgon.datalink.home.models.User;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    RecyclerView userListRecyclerView;
    ProgressBar progressBar;
    DatabaseHelper myDb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        userListRecyclerView = findViewById(R.id.userListRecyclerView);
        progressBar = findViewById(R.id.progressLoadingId);
        myDb = new DatabaseHelper(this);

        progressBar.setVisibility(View.VISIBLE);
        ArrayList<User> users = (ArrayList<User>) myDb.getAllData();
        progressBar.setVisibility(View.GONE);


        Log.i("Info", String.valueOf(myDb.getAllData().size()));

        UserAdapter userAdapter = new UserAdapter(users, ListActivity.this);
        userListRecyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        userListRecyclerView.setAdapter(userAdapter);

        userAdapter.setClickListener(new UserClickLister() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ListActivity.this, "Clicked at position: " + position, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ListActivity.this, UpdateActivity.class);
                intent.putExtra("updateID", users.get(position).getId());
                intent.putExtra("updateEMAIL", users.get(position).getEmail());
                intent.putExtra("updatePHONE", users.get(position).getPhone());
                startActivity(intent);
            }
        });

    }
}