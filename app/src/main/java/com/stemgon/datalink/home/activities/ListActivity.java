package com.stemgon.datalink.home.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.stemgon.datalink.R;
import com.stemgon.datalink.home.adapters.UserAdapter;
import com.stemgon.datalink.home.listeners.UserClickLister;
import com.stemgon.datalink.home.models.User;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    RecyclerView userListRecyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        userListRecyclerView = findViewById(R.id.userListRecyclerView);

        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < 50; i++) {
            users.add(new User(i, "crn96m@gmail.com.com", "0677352242"));
        }

        UserAdapter userAdapter = new UserAdapter(users, ListActivity.this);
        userListRecyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        userListRecyclerView.setAdapter(userAdapter);

        userAdapter.setClickListener(new UserClickLister() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ListActivity.this, "Clicked at position: " + position, Toast.LENGTH_LONG).show();
            }
        });

    }
}