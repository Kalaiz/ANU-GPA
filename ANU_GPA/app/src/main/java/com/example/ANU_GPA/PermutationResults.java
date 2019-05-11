package com.example.ANU_GPA;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


/*Authorship: Kalai (u6555407)*/

public class PermutationResults extends AppCompatActivity {
    TableLayout possibleOutputsTableLayout;
    boolean done=true;
    boolean donePermutation=false;


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
        colNames=(numOfFailsNeeded)?new String[]{"HDs","Ds","CRs","Ps","Fs"}:new String[]{"HDs","Ds","CRs","Ps"};
        p.calculatePermutation();
        TableLayout headingTableLayout=findViewById(R.id.headingTableLayout);
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

                    valueTextView =new TextView(this);
                    valueTextView.setText(val+"\n");
                    valueTextView.setTextSize(20);
                    valueRow.addView(valueTextView);
                }
                if(numOfFailsNeeded) {
                   valueTextView =new TextView(this);
                    valueTextView.setText(0+" ");
                    valueTextView.setTextSize(20);
                    valueRow.addView(valueTextView);
                }
                possibleOutputsTableLayout.addView(valueRow);
            }
            possibleOutputsTableLayout.setStretchAllColumns(true);

        }
        else{
           Toast.makeText(this,"No Possible Permutation",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        final SharedPreferences sharedPreferences = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
        final int nCoursesDone = sharedPreferences.getInt("numOfTCourses", 0);
        final float cgpa = sharedPreferences.getFloat("cgpa", 0);
        final int numTBTCourses = getIntent().getExtras().getInt("numOfTBTCourses");
        final float gpaWanted = getIntent().getExtras().getFloat("gpaWanted");
        final boolean numOfFailsNeeded = getIntent().getExtras().getBoolean("numOfFailsNeeded", false);
        final ScrollView innerScrollView = findViewById(R.id.innerScrollView);
        Toast.makeText(this,"Scroll For More", Toast.LENGTH_SHORT).show();
        innerScrollView.setSmoothScrollingEnabled(true);
        final PermutationGenerator pg = new PermutationGenerator(cgpa, nCoursesDone, numTBTCourses + nCoursesDone, gpaWanted);
        pg.initialise();
        if(numOfFailsNeeded) {
            possibleOutputsTableLayout=findViewById(R.id.possibleResultsTableLayout);
            new InfoLoader().execute(pg);
        }
      innerScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
          @Override
          public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
              if (!donePermutation && done && numOfFailsNeeded&&pg.hasNext()) {
                          new InfoLoader().execute(pg);
                      }

          }
      });
    }



    public class InfoLoader extends  AsyncTask<PermutationGenerator,String,ArrayList<Integer[]>>{
        @Override
        protected ArrayList<Integer[]> doInBackground(PermutationGenerator... permutationGenerators) {
            done=false;
            ArrayList<Integer[]> output=new ArrayList<>();
            PermutationGenerator pg=permutationGenerators[0];
            Integer[] permutation;
            int n=0;
            while(n<101 && pg.hasNext()){
                n++;
                if((permutation=pg.next())!=null){
                    output.add(permutation);
                }else{
                    donePermutation=true;
                    break;
                }
            }
            return output;
        }
        @Override
        protected void onPostExecute( ArrayList<Integer[]> result) {

            for (Integer[] permutation : result) {
                TableRow valueRow2 = new TableRow(PermutationResults.this);
                for (int i = 1; i < 5; i++) {
                    TextView valueTextView = new TextView(PermutationResults.this);
                    valueTextView.setText(permutation[i] + "\n");
                    valueTextView.setTextSize(20);
                    valueRow2.addView(valueTextView);
                }
                TextView valueTextView = new TextView(PermutationResults.this);
                valueTextView.setText(permutation[0] + "\n");
                valueTextView.setTextSize(20);
                valueRow2.addView(valueTextView);
                possibleOutputsTableLayout.addView(valueRow2);

            }
            possibleOutputsTableLayout.setStretchAllColumns(true);
            done=true;

        }






    }

}

