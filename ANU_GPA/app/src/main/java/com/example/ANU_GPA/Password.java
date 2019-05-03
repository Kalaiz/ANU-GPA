package com.example.ANU_GPA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*Authorship Prateek Arora (u6742441)*/
/*main function authored by Jared Graf*/

public class Password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        final SharedPreferences pass = getSharedPreferences("com.example.ANU_GPA.Passwords", MODE_PRIVATE);
        final Button set = findViewById(R.id.lock_button);
        final EditText oldPass = findViewById(R.id.old_pass_edittext);
        final EditText newPass = findViewById(R.id.pass_edittext);
        final EditText newPass2 = findViewById(R.id.confirm_pass_edittext);
        final String actualPass = pass.getString("password", "");
        set.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View l){
                if(oldPass.getText().toString().equals(actualPass)){
                    if(newPass.getText().toString().equals(newPass2.getText().toString())){
                        SharedPreferences.Editor edit = pass.edit();
                        edit.putString("password", newPass.getText().toString());
                        edit.commit();
                        finish();
                    }else{
                        Toast failMessage = Toast.makeText(getApplicationContext(), "new passwords do not match", Toast.LENGTH_SHORT);
                        failMessage.show();
                        Intent intent = new Intent(Password.this, Password.class);
                        startActivity(intent);
                    }
                }else{
                    Toast failMessage = Toast.makeText(getApplicationContext(), "old password is not correct", Toast.LENGTH_SHORT);
                    failMessage.show();
                    Intent intent = new Intent(Password.this, Password.class);
                    startActivity(intent);
                }
                finish();

            }
        });

    }
}