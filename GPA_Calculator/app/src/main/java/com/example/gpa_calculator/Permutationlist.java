package com.example.gpa_calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

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
        TextView possibleOutputs =findViewById(R.id.PossiblePermutationTextView);
        ArrayList<Integer[]> permutations=p.getPermutation();
        //TODO: Use a better implementation
        possibleOutputs.append("\t \t \tnHD nD nCRs nP nF");
        for(Integer[] op:permutations){
            possibleOutputs.append("\n \t \t \t");
            possibleOutputs.append(op[0]+ "\t\t"+ op[1]+ "\t\t"+ op[2]+ "\t\t"+ op[3]+ "\t\t"+ op[4]);

        }


    }
}
