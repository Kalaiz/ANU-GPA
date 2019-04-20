package com.example.ANU_GPA;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

/*Authorship: Kalai (u6555407)*/

public class PermutationResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutation_results);
        //Retrieving values from sharedPreference
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        int nCoursesDone=sharedPreferences.getInt("numOfTCourses",0);
        float cgpa=sharedPreferences.getFloat("cgpa",0);
        int numTBTCourses=getIntent().getExtras().getInt("numOfTBTCourses");
        float gpaWanted=getIntent().getExtras().getFloat("gpaWanted");
        Permutation p = new Permutation(cgpa,nCoursesDone,nCoursesDone+numTBTCourses,gpaWanted);
        ListView possibleOutputs=findViewById(R.id.PossiblePermutationListView);
        ArrayAdapter itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item);
        for(Integer[] s :p.getPermutation()){
            itemsAdapter.add(Arrays.toString(s));
        }
        possibleOutputs.setAdapter(itemsAdapter);
    }
}

