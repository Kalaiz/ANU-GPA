package com.example.ANU_GPA;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
        Permutation p = new Permutation(cgpa,nCoursesDone,numTBTCourses+nCoursesDone,gpaWanted);
        TableLayout possibleOutputs=findViewById(R.id.tableLayout);
        TableRow headingRow=new TableRow(this);
        String[] colNames={"HDs","Ds","CRs","Ps"};

        /*Setting the headings*/
        for(String colName:colNames){
            TextView colTextView=new TextView(this);
            colTextView.setText(colName);
            colTextView.setTextSize(25);
            colTextView.setTextColor(Color.BLACK);
            headingRow.addView(colTextView);
        }
        possibleOutputs.addView(headingRow);
        possibleOutputs.setStretchAllColumns(true);
        /*Adding values to the table*/
        for(Integer[] s :p.getPermutation()){
            TableRow valueRow=new TableRow(this);
            for(int val :s){
                TextView valueTextView=new TextView(this);
                valueTextView.setText(val+"\n");
                valueTextView.setTextSize(20);
               valueRow.addView(valueTextView);
            }
            possibleOutputs.addView(valueRow);
        }

    }
}

