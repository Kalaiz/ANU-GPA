package com.example.ANU_GPA;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


/**
 * Activity which displays all of the possible permutation based on the user's input.
 * @author: Kalai (u6555407)*/
public class PermutationResults extends AppCompatActivity {
    TableLayout possibleOutputsTableLayout;
    boolean done;
    boolean donePermutation;
    boolean doubleTap=false;
    int fetch=20; //Initial number of permutations being fetch for number of fails known
    ArrayList<AsyncTask> asyncTasks=new ArrayList<>();
    PermutationGenerator pg;
    boolean numOfFailsNeeded;
    RelativeLayout outerRelativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutation_results);

        //Retrieving values from sharedPreference & Intents
        final SharedPreferences sharedPreferences = getSharedPreferences("com.example.ANU_GPA.Data", Context.MODE_PRIVATE);
        final int nCoursesDone=sharedPreferences.getInt("numOfTCourses",0);
        final float cgpa=sharedPreferences.getFloat("cgpa",0);
        final int numTBTCourses=getIntent().getExtras().getInt("numOfTBTCourses");
        final float gpaWanted=getIntent().getExtras().getFloat("gpaWanted");
        numOfFailsNeeded= getIntent().getExtras().getBoolean("numOfFailsNeeded", false);

        /*Creating respective Permutation Object in accordance to the requirement */
        String[] colNames;
        ArrayList<Integer[]> permutations;
        if(numOfFailsNeeded){
            colNames=new String[]{"HDs","Ds","CRs","Ps","Fs"};
            pg=new PermutationGenerator(cgpa,nCoursesDone,numTBTCourses+nCoursesDone,gpaWanted);
            pg.calculatePermutation();
            pg.initialise();
            permutations=pg.getPermutation();
        }
        else{
            colNames=new String[]{"HDs","Ds","CRs","Ps"};
            Permutation p=new Permutation(cgpa,nCoursesDone,numTBTCourses+nCoursesDone,gpaWanted);
            p.calculatePermutation();
            permutations=p.getPermutation();
        }

        /*Obtaining Needed View Objects*/
        outerRelativeLayout=findViewById(R.id.outerRelativeLayout);
        possibleOutputsTableLayout=findViewById(R.id.possibleResultsTableLayout);
        TableLayout headingTableLayout=findViewById(R.id.headingTableLayout);
        ScrollView innerScrollView = findViewById(R.id.innerScrollView);
        innerScrollView.setSmoothScrollingEnabled(true);

        if(permutations.size()>0) {
            /*Setting the Heading Row And it's attributes*/
            TableRow headingRow=new TableRow(this);
            for(String colName:colNames){
                TextView colTextView=new TextView(this);
                colTextView.setText(colName);
                colTextView.setTextSize(25);
                colTextView.setTextColor(Color.BLACK);
                headingRow.addView(colTextView);
            }
            headingTableLayout.addView(headingRow);
            headingTableLayout.setStretchAllColumns(true);

            /*Adding values/rows to the table*/
            TextView valueTextView ;
            TableRow valueRow;
            for (Integer[] s : permutations) {
                valueRow = new TableRow(this);
                for (int val : s) {
                    valueTextView =new TextView(this);
                    valueTextView.setText(val+"\n");
                    valueTextView.setTextSize(20);
                    valueRow.addView(valueTextView);
                }
                if(numOfFailsNeeded) {
                    /*if number of fails is needed,since for number of fails = 0 situation; permutation
                    from permutation class can be used and a column of zero can be filled in the number of fail*/
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
        done = true;
        donePermutation = false;
        if (numOfFailsNeeded) {
            fetch = 30;
            new InfoLoader().execute(pg);
            Toast.makeText(this, "Double Tap For More", Toast.LENGTH_SHORT).show();
        }
        /*If only few permutation are there and the user double taps*/
        outerRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( doubleTap = !doubleTap)
                Toast.makeText(PermutationResults.this, "No more possible Permutations.", Toast.LENGTH_SHORT).show();
            }
        });

        possibleOutputsTableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numOfFailsNeeded&&(doubleTap = !doubleTap)) {
                    boolean running = false;
                    for (AsyncTask asyncTask : asyncTasks) {
                        if (asyncTask.getStatus() == AsyncTask.Status.RUNNING) {
                            Toast.makeText(PermutationResults.this,"Still fetching permutations,Please wait!",Toast.LENGTH_LONG).show();
                            running = true;
                            break;
                        }
                    }
                    if (!running) {
                        if (!donePermutation && done && pg.hasNext()) {
                            Toast.makeText(PermutationResults.this,"Please Wait",Toast.LENGTH_LONG).show();
                            fetch = 150;
                            asyncTasks.add(new InfoLoader().execute(pg));
                        }
                    }
                }
                else{
                    if(donePermutation||!numOfFailsNeeded) {
                        Toast.makeText(PermutationResults.this, "No more possible Permutations.", Toast.LENGTH_SHORT).show();
                    }}
            }
        });
    }

    /**
     * Making sure that there is'nt any asynctasks running when the user presses back */
    @Override
    public void onBackPressed() {
        for(AsyncTask asyncTask:asyncTasks){
            asyncTask.cancel(true);
        }
        boolean cancel=true;
        for(AsyncTask asyncTask:asyncTasks){
            cancel=cancel&& asyncTask.isCancelled();
        }
        if(cancel) {

            super.onBackPressed();
        }
    }

    /** A class which runs on a separate thread in parallel to the UI thread*/
    public class InfoLoader extends  AsyncTask<PermutationGenerator,String,ArrayList<Integer[]>>{
        @Override
        protected ArrayList<Integer[]> doInBackground(PermutationGenerator... permutationGenerators) {
            done=false;
            ArrayList<Integer[]> output=new ArrayList<>();
            PermutationGenerator pg=permutationGenerators[0];
            Integer[] permutation;
            int n=0;
            while(n<fetch&& pg.hasNext()){
                n++;
                if(!isCancelled()&&(permutation=pg.next())!=null){
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
            if(fetch>31) {
                Toast.makeText(PermutationResults.this, "Permutations Fetched! \n Double Tap For More.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}