package com.stemgon.datalink.home.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.stemgon.datalink.R;
import com.stemgon.datalink.home.database.helpers.DatabaseHelper;
import com.stemgon.datalink.home.models.User;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class UpdateActivity extends AppCompatActivity {
    Button updateBtn;
    EditText emailEdtText, phoneEdText;
    DatabaseHelper myDb;

    RecyclerView userListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        myDb = new DatabaseHelper(this);
        updateBtn = findViewById(R.id.addBtn);
        emailEdtText = findViewById(R.id.editTextTextEmailAddress);
        phoneEdText = findViewById(R.id.editTextPhone);
        Intent intent = getIntent();

        ArrayList<User> users = (ArrayList<User>) myDb.getAllData();
        int id = intent.getIntExtra("updateID", 0);
        emailEdtText.setText(users.get(id).getEmail());
        phoneEdText.setText(users.get(id).getPhone());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdtText.getText().toString();
                String phone = phoneEdText.getText().toString();
                addDataToDatabase(id, email, phone);
            }

        });
    }


    private void addDataToDatabase(Integer id, String email, String phone) {
        if (dataIsValid(email, phone)) {
            startWarningDialogue(id, email, phone);
        }else{
            Toast.makeText(UpdateActivity.this, "Data is not valid", Toast.LENGTH_LONG).show();
        }
    }

    private void startWarningDialogue(Integer id, String email, String phone) {

        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setTitle("Information");
        builder.setMessage("Data is valid, email: " + email + " phone: "+ phone);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean inserted = updateData(id, email, phone);
                Toast.makeText(UpdateActivity.this, "Your data was stored", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private boolean updateData(Integer id, String email, String phone) {
        boolean isInserted = myDb.updateItem(id, email, phone);
        if (isInserted)
            Toast.makeText(UpdateActivity.this, "Data updated", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(UpdateActivity.this, "Data not updated", Toast.LENGTH_LONG).show();
        return isInserted;
    }


    private boolean dataIsValid(String email, String phone) {
        return isValid(email) && !email.isEmpty() && !phone.isEmpty() && phone.length() == 10;
    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}