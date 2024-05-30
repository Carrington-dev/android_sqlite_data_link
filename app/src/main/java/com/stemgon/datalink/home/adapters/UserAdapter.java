package com.stemgon.datalink.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stemgon.datalink.R;
import com.stemgon.datalink.home.holders.UserHolder;
import com.stemgon.datalink.home.listeners.UserClickLister;
import com.stemgon.datalink.home.models.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyUserHolder> {
    ArrayList<User> userList;
    Context context;
    UserClickLister userClickLister;



    public UserAdapter(ArrayList<User> userList, Context context) {
        this.userList = userList;
        this.context = context;

    }

    //    @NonNull
    //    @Override
    //    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //        return new UserHolder(LayoutInflater.from(context).inflate(R.layout.user_detail, null, false));
    //    }

    @NonNull
    @Override
    public MyUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyUserHolder(LayoutInflater.from(context).inflate(R.layout.user_detail,
                null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyUserHolder holder, int position) {
        holder.phone.setText("Phone Number: " + userList.get(position).getPhone());
        holder.id.setText(userList.get(position).getId().toString());
        holder.email.setText(userList.get(position).getEmail());
    }

    //    @Override
    //    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
    //        holder.phone.setText("Phone Number: " + userList.get(position).getPhone());
    //        holder.id.setText(userList.get(position).getId().toString());
    //        holder.email.setText(userList.get(position).getEmail());
    //
    //
    //    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    // allows clicks events to be caught
    public void setClickListener(UserClickLister itemClickListener) {
        this.userClickLister = itemClickListener;
    }

    // stores and recycles views as they are scrolled off screen
    public class MyUserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView id, email, phone;

        MyUserHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            email = itemView.findViewById(R.id.emialId);
            phone = itemView.findViewById(R.id.phoneId);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (userClickLister != null) userClickLister.onItemClick(view, getAdapterPosition());
        }
    }

}
