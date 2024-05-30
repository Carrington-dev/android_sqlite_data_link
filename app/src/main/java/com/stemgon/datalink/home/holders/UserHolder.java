package com.stemgon.datalink.home.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stemgon.datalink.R;

public class UserHolder extends RecyclerView.ViewHolder {
    public TextView id, email, phone;
    public UserHolder(@NonNull View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.id);
        email = itemView.findViewById(R.id.emialId);
        phone = itemView.findViewById(R.id.phoneId);
    }
}
