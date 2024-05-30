package com.stemgon.datalink.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stemgon.datalink.R;
import com.stemgon.datalink.home.holders.UserHolder;
import com.stemgon.datalink.home.models.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {
    ArrayList<User> userList;
    Context context;

    public UserAdapter(ArrayList<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserHolder(LayoutInflater.from(context).inflate(R.layout.user_detail, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.phone.setText(userList.get(position).getPhone());
        holder.id.setText(userList.get(position).getId().toString());
        holder.email.setText(userList.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
