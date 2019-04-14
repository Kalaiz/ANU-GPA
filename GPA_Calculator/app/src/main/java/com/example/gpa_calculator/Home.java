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

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gp);
        final TextView cgpaTextView =(TextView) findViewById(R.id.CGPATextView);
        final EditText cgpaEditText=(EditText) findViewById(R.id.CGPAEditText);
        final TextView numOfCourseDoneTextView = (TextView) findViewById(R.id.numOfCourseDoneTextView);
        final EditText numOfCourseDoneEditText=(EditText) findViewById(R.id.numOfCourseDoneEditText);
        final TextView no_of_courses_have_to_do  = (TextView) findViewById(R.id.text4);
        final EditText num_of_courses_have_to_do_edit = (EditText) findViewById(R.id.numOfTBTCourseEditText);
        final TextView GPA_want_to_achieve  = (TextView) findViewById(R.id.text4);
        final EditText GPA_want_to_achieve_edit = (EditText) findViewById(R.id.numOfTBTCourseEditText);
        Button know = (Button) findViewById(R.id.know);
        Button dontknow = (Button) findViewById(R.id.dknow);
        Button submit = (Button) findViewById(R.id.submit);
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numOfCourseDoneTextView.setVisibility(View.VISIBLE);
                numOfCourseDoneEditText.setVisibility(View.VISIBLE);
                cgpaTextView.setVisibility(View.VISIBLE);
                cgpaEditText.setVisibility(View.VISIBLE);


            }
        });

        know.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                numOfCourseDoneTextView.setVisibility(View.VISIBLE);
                numOfCourseDoneEditText.setVisibility(View.VISIBLE);
                cgpaTextView.setVisibility(View.VISIBLE);
                cgpaEditText.setVisibility(View.VISIBLE);
                return true;
            }
        });


        dontknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, GPAcalc.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("nCoursesDone",Integer.parseInt(((EditText)findViewById(R.id.numOfCourseDoneEditText)).getText().toString()));
                editor.putInt("nTBTCourses",Integer.parseInt(((((EditText)findViewById(R.id.numOfTBTCourseEditText)).getText().toString()))));
                editor.putFloat("gpaWanted",Float.parseFloat(((EditText)findViewById(R.id.gpaWantedEditText)).getText().toString()));
                editor.putFloat("CGPA",Float.parseFloat(((EditText)findViewById(R.id.CGPAEditText)).getText().toString()));
                Toast.makeText(Home.this,"Got the marks",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Home.this,Permutationlist.class);
                startActivity(intent);
            }
        });
    }
}
