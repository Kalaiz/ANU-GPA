package com.example.ANU_GPA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PasswordSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SharedPreferences pass = getSharedPreferences("com.example.ANU_GPA.Passwords", MODE_PRIVATE);
        setContentView(R.layout.activity_password_set);
        final Button set = findViewById(R.id.button);
        final EditText passwordChosen = findViewById(R.id.editText);
        set.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View l){
                String temp = passwordChosen.getText().toString();
                SharedPreferences.Editor edit = pass.edit();
                edit.putString("password", temp);
                edit.commit();
                Intent intent = new Intent(PasswordSet.this, Settings.class);
                startActivity(intent);
            }
        });
    }


}
