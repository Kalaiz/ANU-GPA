package com.example.ANU_GPA;

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

public class PermutationCalc extends AppCompatActivity {
    boolean viewToggle =true;
    int visibility =View.VISIBLE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutationcalc);
        final TextView cgpaTextView =(TextView) findViewById(R.id.CGPATextView);
        final EditText cgpaEditText=(EditText) findViewById(R.id.CGPAEditText);
        final TextView numOfCourseDoneTextView = (TextView) findViewById(R.id.numOfCourseDoneTextView);
        final EditText numOfCourseDoneEditText=(EditText) findViewById(R.id.numOfCourseDoneEditText);
        Button know = (Button) findViewById(R.id.know);
        Button dontknow = (Button) findViewById(R.id.dknow);
        Button submit = (Button) findViewById(R.id.submit);
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visibility =viewToggle?View.VISIBLE:View.INVISIBLE; //For toggling effect
                viewToggle=!viewToggle;
                numOfCourseDoneTextView.setVisibility(visibility);
                numOfCourseDoneEditText.setVisibility(visibility);
                cgpaTextView.setVisibility(visibility);
                cgpaEditText.setVisibility(visibility);
            }
        });

        dontknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermutationCalc.this, GPACalc.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    try{
                        //if CGPA is not updated it means that user is about to type in his/her CGPA.--preventing overwrite
                        if(sharedPreferences.getFloat("CGPA",-1)==-1) {//CGPA might be 0.
                            editor.putInt("numOfTCourses", Integer.parseInt(((EditText) findViewById(R.id.numOfCourseDoneEditText))
                                    .getText().toString()));
                            editor.putFloat("CGPA",Float.parseFloat(((EditText)findViewById(R.id.CGPAEditText))
                                    .getText().toString()));
                        }
                        editor.putInt("numOfTBTCourses",Integer.parseInt(((((EditText)findViewById(R.id.numOfTBTCourseEditText))
                                .getText().toString()))));
                        editor.putFloat("gpaWanted",Float.parseFloat(((EditText)findViewById(R.id.gpaWantedEditText))
                                .getText().toString()));
                        editor.apply();}
                    catch(NumberFormatException n){
                        Toast.makeText(PermutationCalc.this,"Wrong input ",Toast.LENGTH_LONG).show();
                    }
                    boolean error=sharedPreferences.getInt("numOfTBTCourses",0)==0
                                   &&sharedPreferences.getFloat("gpaWanted",0)==0;
                if(!error){
                Toast.makeText(PermutationCalc.this,"Got the marks",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(PermutationCalc.this,PermutationResults.class);
                startActivity(intent);}
            }
        });
    }
}
