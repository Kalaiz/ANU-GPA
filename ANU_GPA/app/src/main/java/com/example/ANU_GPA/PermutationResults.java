package com.example.ANU_GPA;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
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
        final SharedPreferences sharedPreferences = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
        final int nCoursesDone=sharedPreferences.getInt("numOfTCourses",0);
        final float cgpa=sharedPreferences.getFloat("cgpa",0);
        final int numTBTCourses=getIntent().getExtras().getInt("numOfTBTCourses");
        final float gpaWanted=getIntent().getExtras().getFloat("gpaWanted");
        final Permutation p=new Permutation(cgpa,nCoursesDone,numTBTCourses+nCoursesDone,gpaWanted);
        final boolean numOfFailsNeeded= getIntent().getExtras().getBoolean("numOfFailsNeeded", false);
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
        final TableLayout possibleOutputsTableLayout=findViewById(R.id.possibleResultsTableLayout);
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


        }
        else{
            Toast noResults=Toast.makeText(this,"No Possible Permutation",Toast.LENGTH_LONG);
            noResults.show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        final SharedPreferences sharedPreferences = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
        final int nCoursesDone=sharedPreferences.getInt("numOfTCourses",0);
        final float cgpa=sharedPreferences.getFloat("cgpa",0);
        final int numTBTCourses=getIntent().getExtras().getInt("numOfTBTCourses");
        final float gpaWanted=getIntent().getExtras().getFloat("gpaWanted");
        final boolean numOfFailsNeeded= getIntent().getExtras().getBoolean("numOfFailsNeeded", false);
        final TableLayout possibleOutputsTableLayout=findViewById(R.id.possibleResultsTableLayout);
        final ScrollView innerScrollView=findViewById(R.id.innerScrollView);
        innerScrollView.setSmoothScrollingEnabled(true);
        final PermutationGenerator pg
                =new PermutationGenerator(cgpa,nCoursesDone,numTBTCourses+nCoursesDone,gpaWanted);
        Toast.makeText(PermutationResults.this,"Loading Data",Toast.LENGTH_LONG).show();

        innerScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY-oldScrollY>40 && pg.hasNext()){
                if(numOfFailsNeeded){
                    int n=0;
                            if( pg.hasNext()){
                                n++;
                                Integer[] output= pg.next();
                                if(output!=null){
                                    TableRow valueRow2 = new TableRow(PermutationResults.this);
                                    for (int i=1;i<5;i++) {
                                        TextView valueTextView = new TextView(PermutationResults.this);
                                        valueTextView.setText(output[i]+"\n");
                                        valueTextView.setTextSize(20);
                                        valueRow2.addView(valueTextView);
                                    }
                                    TextView valueTextView = new TextView(PermutationResults.this);
                                    valueTextView.setText(output[0]+"\n");
                                    valueTextView.setTextSize(20);
                                    valueRow2.addView(valueTextView);
                                    possibleOutputsTableLayout.addView(valueRow2);

                                }

                            }
                }

            }}
        });

        possibleOutputsTableLayout.setStretchAllColumns(true);


    }

}

