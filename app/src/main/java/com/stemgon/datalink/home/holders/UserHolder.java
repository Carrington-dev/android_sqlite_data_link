package com.stemgon.datalink.home.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stemgon.datalink.R;
import com.stemgon.datalink.home.listeners.UserClickLister;

public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView id, email, phone;
    public UserClickLister userClickLister;

    public UserHolder(@NonNull View itemView) {
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
