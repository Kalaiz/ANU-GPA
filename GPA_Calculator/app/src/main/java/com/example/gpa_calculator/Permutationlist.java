package com.example.gpa_calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

public class Permutationlist extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutationlist);
        //Retrieving values from sharedPreference
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        int nCoursesDone=sharedPreferences.getInt("nCoursesDone",0);
        int numTBTCourses=sharedPreferences.getInt("numTBTCourses",0);
        float gpaWanted=sharedPreferences.getFloat("gpaWanted",0);
        float cgpa=sharedPreferences.getFloat("CGPA",0);
        Permutation p = new Permutation(cgpa,nCoursesDone,numTBTCourses+nCoursesDone,gpaWanted);
        TextView possibleOutputs =findViewById(R.id.PossiblePermutationEditText);
        String s="hi";
        for(Object o:p.getPermutation()){
           int[] op=(int[]) o ;
            s=s.concat("hello ");
        }
        possibleOutputs.setText(s);

    }
}
