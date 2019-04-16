package com.example.ANU_GPA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PermutationCalc extends AppCompatActivity {
    // Below two variables for knowButton
    boolean viewToggle =true;
    int visibility =View.VISIBLE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutationcalc);
        final TextView cgpaTextView =(TextView) findViewById(R.id.cgpaTextView);
        final EditText cgpaEditText=(EditText) findViewById(R.id.cgpaEditText);
        final TextView numOfCourseDoneTextView = (TextView) findViewById(R.id.numOfCourseDoneTextView);
        final EditText numOfCourseDoneEditText=(EditText) findViewById(R.id.numOfCourseDoneEditText);
        final Button knowButton = (Button) findViewById(R.id.knowButton);
        final Button dKnowButton = (Button) findViewById(R.id.dKnowButton);
        final Button submitButton = (Button) findViewById(R.id.submitButton);
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final RecyclerView recyclerView=findViewById(R.id.recyclerView);
        ArrayList<View> views=new ArrayList<>();
        views.add(new TextView(this));

        //TODO
        //fill in the recyclerView with local storage values such as cgpa, numOfTBTCourses ,numOfTCourses &  gpaWanted.
        // & Display them.

       /* String[] //might wanna use the future method in Settings class
        for(int i=0;i<5;i++)
        {
            TextView text = new TextView(this);
            text.setText(""+i);
            views.add(text);
        }*/

        knowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButton.animate().translationYBy(viewToggle ? 400 : -400);//Moving submit button accordingly.
                visibility =viewToggle?View.VISIBLE:View.INVISIBLE; //For toggling effect.
                viewToggle=!viewToggle;
                numOfCourseDoneTextView.setVisibility(visibility);
                numOfCourseDoneEditText.setVisibility(visibility);
                cgpaTextView.setVisibility(visibility);
                cgpaEditText.setVisibility(visibility);
            }
        });

        dKnowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermutationCalc.this, GPACalc.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //if cgpa is not updated it means that user is about to type in his/her cgpa.--preventing overwriting
                    if(sharedPreferences.getFloat("cgpa",-1)==-1) {//cgpa might be 0.
                        editor.putInt("numOfTCourses", Integer.parseInt(((EditText) findViewById(R.id.numOfCourseDoneEditText))
                                .getText().toString()));
                        editor.putFloat("cgpa",Float.parseFloat(((EditText)findViewById(R.id.cgpaEditText))
                                .getText().toString()));
                    }
                    editor.putInt("numOfTBTCourses",Integer.parseInt(((((EditText)findViewById(R.id.numOfTBTCourseEditText))
                            .getText().toString()))));
                    editor.putFloat("gpaWanted",Float.parseFloat(((EditText)findViewById(R.id.gpaWantedEditText))
                            .getText().toString()));
                    editor.apply();}
                catch(NumberFormatException n){
                    Toast.makeText(PermutationCalc.this,"Wrong input ",Toast.LENGTH_LONG).show();
                }
                boolean error=sharedPreferences.getInt("numOfTBTCourses",0)==0
                        &&sharedPreferences.getFloat("gpaWanted",0)==0;
                if(!error){
                    Toast.makeText(PermutationCalc.this,"Got the Permutations",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PermutationCalc.this,PermutationResults.class);
                    startActivity(intent);}
            }
        });
    }
}
