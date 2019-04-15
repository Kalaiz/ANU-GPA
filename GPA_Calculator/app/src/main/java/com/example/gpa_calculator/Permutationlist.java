package com.example.gpa_calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Permutationlist extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutationlist);
        //Retrieving values from sharedPreference
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        int nCoursesDone=sharedPreferences.getInt("numOfTCourses",0);
        int numTBTCourses=sharedPreferences.getInt("numOfTBTCourses",0);
        float gpaWanted=sharedPreferences.getFloat("gpaWanted",0);
        float cgpa=sharedPreferences.getFloat("CGPA",0);
        Permutation p = new Permutation(cgpa,nCoursesDone,nCoursesDone+numTBTCourses,gpaWanted);
        ListView possibleOutputs=findViewById(R.id.PossiblePermutationListView);
        ArrayAdapter itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item);
        for(Integer[] s :p.getPermutation()){
                 itemsAdapter.add(Arrays.toString(s));
        }        possibleOutputs.setAdapter(itemsAdapter);

        }

    }

