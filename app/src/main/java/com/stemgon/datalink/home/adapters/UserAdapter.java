package com.stemgon.datalink.home.adapters;

import static android.content.Intent.getIntent;
import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stemgon.datalink.R;
import com.stemgon.datalink.home.activities.ListActivity;
import com.stemgon.datalink.home.database.helpers.DatabaseHelper;
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
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull UserAdapter.MyUserHolder holder, int position) {
        holder.phone.setText("Phone Number: " + userList.get(position).getPhone());
        holder.id.setText(userList.get(position).getId().toString());
        holder.email.setText(userList.get(position).getEmail());

        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(context);
                boolean isDeleted = databaseHelper.deleteItem(userList.get(position).getId());
                if (isDeleted){
                    Toast.makeText(context, "Deleted id: " + userList.get(position).getId(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context, "Failed to delete user id: " + userList.get(position).getId(), Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        */
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
        ImageView deleteIcon;

        MyUserHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            email = itemView.findViewById(R.id.emailId);
            phone = itemView.findViewById(R.id.phoneId);
            deleteIcon = itemView.findViewById(R.id.iconID);

            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View view) {
            if (userClickLister != null) userClickLister.onItemClick(view, getAdapterPosition());
        }
    }

}
