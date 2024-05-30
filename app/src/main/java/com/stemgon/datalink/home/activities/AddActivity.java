package com.stemgon.datalink.home.activities;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stemgon.datalink.R;

import java.util.regex.Pattern;

public class AddActivity extends AppCompatActivity {
    Button addBtn;
    EditText emailEdtText, phoneEdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addBtn = findViewById(R.id.addBtn);
        emailEdtText = findViewById(R.id.editTextTextEmailAddress);
        phoneEdText = findViewById(R.id.editTextPhone);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdtText.getText().toString();
                String phone = phoneEdText.getText().toString();
                addDataToDatabase(email, phone);
            }

        });
    }



    private void addDataToDatabase(String email, String phone) {
        if (dataIsValid(email, phone)) {
            startWarningDialogue(email, phone);
        }else{
            Toast.makeText(AddActivity.this, "Data is not valid", Toast.LENGTH_LONG).show();
        }
    }

    private void startWarningDialogue(String email, String phone) {

        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        builder.setTitle("Information");
        builder.setMessage("Data is valid, email: " + email + " phone: "+ phone);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AddActivity.this, "Your data was stored", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddActivity.this, ListActivity.class);
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