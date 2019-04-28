package com.example.ANU_GPA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PasswordEntry extends AppCompatActivity {
    SharedPreferences pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences pass = getSharedPreferences("com.example.ANU_GPA.Passwords", MODE_PRIVATE);
        setContentView(R.layout.activity_password_entry);
        final Button unlock = findViewById(R.id.button);
        final EditText passwordGiven = findViewById(R.id.editText);

        unlock.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View l){
                if(passwordGiven.getText().toString() == pass.getString("password", "")){
                    Intent intent = new Intent(PasswordEntry.this, Home.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(PasswordEntry.this, PasswordEntry.class);
                    startActivity(intent);
                }

            }
        });
    }


}
