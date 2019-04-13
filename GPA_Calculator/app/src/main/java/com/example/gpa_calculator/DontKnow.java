package com.example.gpa_calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DontKnow extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dont_know);

        Button done = (Button) findViewById(R.id.done);
       final EditText hd = (EditText) findViewById(R.id.hd_edit);
       final EditText d = (EditText) findViewById(R.id.d_edit) ;
       final EditText c = (EditText) findViewById(R.id.c_edit);
       final EditText pass = (EditText) findViewById(R.id.pass_edit) ;
       final EditText fail = (EditText) findViewById(R.id.fail_edit);
        final SharedPreferences sharedPreferences = getSharedPreferences("grades", Context.MODE_PRIVATE);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("hd",hd.getText().toString());
                editor.putString("d",d.getText().toString());
                editor.putString("c",c.getText().toString());
                editor.putString("pass",pass.getText().toString());
                editor.putString("fail",fail.getText().toString());
                Toast.makeText(DontKnow.this,"Extract marks",Toast.LENGTH_LONG).show();
                editor.apply();
                // Here place the code for the calculating cgpa if person doesn't know his gpa and only knows his grades.
                Intent intent = new Intent(DontKnow.this,GPA.class);
                startActivity(intent);
            }
        });

    }
}
