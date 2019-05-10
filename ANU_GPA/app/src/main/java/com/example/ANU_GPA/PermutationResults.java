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
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*Authorship: Kalai (u6555407)*/

public class PermutationResults extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutation_results);
        //Retrieving values from sharedPreference
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        final int nCoursesDone=sharedPreferences.getInt("numOfTCourses",0);
        final float cgpa=sharedPreferences.getFloat("cgpa",0);
        final int numTBTCourses=getIntent().getExtras().getInt("numOfTBTCourses");
        final float gpaWanted=getIntent().getExtras().getFloat("gpaWanted");
        final Permutation p=new Permutation(cgpa,nCoursesDone,numTBTCourses+nCoursesDone,gpaWanted);
        boolean numOfFailsNeeded= getIntent().getExtras().getBoolean("numOfFailsNeeded", false);
        String[] colNames;
        if (numOfFailsNeeded){
            p.calculatePermutation();
            colNames=new String[]{"HDs","Ds","CRs","Ps","Fs"};
        }
        else{
            p.calculatePermutation();
            colNames=new String[]{"HDs","Ds","CRs","Ps"};
        }

        TableLayout headingTableLayout=findViewById(R.id.headingTableLayout);
        headingTableLayout.setGravity(View.TEXT_ALIGNMENT_CENTER);
        TableLayout possibleOutputsTableLayout=findViewById(R.id.possibleResultsTableLayout);
        TableRow headingRow=new TableRow(this);
        ArrayList<Integer[]> permutations=p.getPermutation();
        if(permutations.size()>0) {
        /*Setting the headings*/
        for(String colName:colNames){
            TextView colTextView=new TextView(this);
            colTextView.setText(colName);
            colTextView.setTextSize(25);
            colTextView.setTextColor(Color.BLACK);
            headingRow.addView(colTextView);

        }
        headingTableLayout.addView(headingRow);
        headingTableLayout.setStretchAllColumns(true);


            TextView valueTextView ;
            TableRow valueRow;
            /*Adding values to the table*/
            for (Integer[] s : permutations) {
                valueRow = new TableRow(this);
                for (int val : s) {
                    valueTextView = new TextView(this);
                    valueTextView.setText(val+"\n");
                    valueTextView.setTextSize(20);
                    valueRow.addView(valueTextView);
                }
                if(numOfFailsNeeded) {
                    valueTextView = new TextView(this);
                    valueTextView.setText(0+" ");
                    valueTextView.setTextSize(20);
                    valueRow.addView(valueTextView);
                }
                possibleOutputsTableLayout.addView(valueRow);


            }
            if(numOfFailsNeeded){
                PermutationGenerator pg =new PermutationGenerator(cgpa,nCoursesDone,numTBTCourses+nCoursesDone,gpaWanted);
               int n=0;
                while(n<50 && pg.hasNext()){
                   Integer[] output= pg.next();
                   if(output!=null){
                       n++;
                       valueRow = new TableRow(this);
                       for (int i=1;i<5;i++) {
                           valueTextView = new TextView(this);
                           valueTextView.setText(output[i]+"\n");
                           valueTextView.setTextSize(20);
                           valueRow.addView(valueTextView);
                       }
                       valueTextView = new TextView(this);
                       valueTextView.setText(output[0]+"\n");
                       valueTextView.setTextSize(20);
                       valueRow.addView(valueTextView);
                       possibleOutputsTableLayout.addView(valueRow);
                   }
                }
            }

            possibleOutputsTableLayout.setStretchAllColumns(true);
        }
        else{
            Toast noResults=Toast.makeText(this,"No Possible Permutation",Toast.LENGTH_LONG);
            noResults.show();
        }

    }
}

