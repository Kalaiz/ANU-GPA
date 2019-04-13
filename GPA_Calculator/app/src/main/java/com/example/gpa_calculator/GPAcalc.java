package com.example.gpa_calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GPAcalc extends AppCompatActivity {

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

        final SharedPreferences sharedPreferences = getSharedPreferences("grades", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

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
                Intent intent = new Intent(GPAcalc.this,DontKnow.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numOfTBTCourses = Integer.parseInt(((((EditText)findViewById(R.id.numOfTBTCourseEditText)).getText().toString())));
                int numOfTCourses =Integer.parseInt(((EditText)findViewById(R.id.numOfCourseDoneEditText)).getText().toString());
                int numOfTotalCourses=numOfTBTCourses+numOfTCourses;//TODO change GPA constructor in accordance
                float gpaWanted =(float) Double.parseDouble(((EditText)findViewById(R.id.gpaWantedEditText)).getText().toString());
                float cgpa=(float)Double.parseDouble(((EditText)findViewById(R.id.CGPAEditText)).getText().toString());
                Permutation p=new Permutation(cgpa,numOfTCourses,numOfTotalCourses,gpaWanted);
                // Here, put the code for the permutation list of ways to achieve a particular grade entered by the user
                Toast.makeText(GPAcalc.this,"Got the marks",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(GPAcalc.this,Permutationlist.class);
                intent.putExtra("PermutationObject", (Parcelable) p);
                startActivity(intent);
            }
        });
    }
}
