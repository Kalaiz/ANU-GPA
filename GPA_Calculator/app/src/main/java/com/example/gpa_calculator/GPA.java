package com.example.gpa_calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GPA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gp);
        final TextView no_of_courses  = (TextView) findViewById(R.id.text3);
        final EditText num_of_courses_edit = (EditText) findViewById(R.id.edit_text1);
        final TextView no_of_courses_have_to_do  = (TextView) findViewById(R.id.text4);
        final EditText num_of_courses_have_to_do_edit = (EditText) findViewById(R.id.edit_text2);
        final TextView GPA_want_to_achieve  = (TextView) findViewById(R.id.text4);
        final EditText GPA_want_to_achieve_edit = (EditText) findViewById(R.id.edit_text2);
        Button know = (Button) findViewById(R.id.know);
        Button dontknow = (Button) findViewById(R.id.dknow);
        Button submit = (Button) findViewById(R.id.submit);

        final SharedPreferences sharedPreferences = getSharedPreferences("grades", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no_of_courses.setVisibility(View.VISIBLE);
                num_of_courses_edit.setVisibility(View.VISIBLE);
            }
        });

        know.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                no_of_courses.setVisibility(View.INVISIBLE);
                num_of_courses_edit.setVisibility(View.INVISIBLE);
                return true;
            }
        });


        dontknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GPA.this,DontKnow.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hd = sharedPreferences.getString("hd","");
                String d = sharedPreferences.getString("d","");
                String c = sharedPreferences.getString("c","");
                String pass = sharedPreferences.getString("pass","");
                String fail = sharedPreferences.getString("fail","");
                // Here, put the code for the permuatation list of ways to achieve a particular grade entered by the user
                Toast.makeText(GPA.this,"Got the marks",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(GPA.this,Permutationlist.class);
                startActivity(intent);
            }
        });
    }
}
