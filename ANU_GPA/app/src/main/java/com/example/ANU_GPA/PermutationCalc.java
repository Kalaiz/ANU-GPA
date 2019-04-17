package com.example.ANU_GPA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Map;

public class PermutationCalc extends AppCompatActivity {
    // Below two variables are for knowButton.
    boolean knownButtonClicked = false;
    int visibility = View.VISIBLE;


    /**
     * Based on given key of SharedPreference with the respective sharedPreference.
     * the value stored can be retrieved.
     * @param key (sharedPreference based) String Key on which data is to be retrieved as it'srespective type.
     * @return The respective value of the key as an String.
     * */
    public <T> T sPreferenceRetriever(String key,SharedPreferences sp) {
        Map<String, ?> data = sp.getAll();
        if(data.containsKey(key)){
            T genericValue=(T)data.get(key);
            if (genericValue.getClass() == Integer.class) {
                return (T)Integer.valueOf(sp.getInt(key, 0))  ;
            }
            if (genericValue.getClass() == Float.class) {
                return (T)Float.valueOf(sp.getFloat(key, 0)) ;
            }
            if (genericValue.getClass() == String.class) {
                return (T)sp.getString(key, "");
            }}
        return (T)"";
    }


    /**
     * Provides a string representation of the given sharedPreference based on the given attributes.
     * @param sp The respective SharedPreference Object.
     * @param attributes An Array of attributes/Keys of sp
     * @return  A String representation of the sharedPreference object.*/
    public String localDataStatus(String[] attributes,SharedPreferences sp){
        String input="Locally Stored Data :  \n";
        for(String attribute:attributes)
        {
            input+= attribute+": " +sPreferenceRetriever(attribute,sp) + " \n";
        }
        return input;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutationcalc);
        final TextView cgpaTextView =(TextView) findViewById(R.id.cgpaTextView);
        final EditText cgpaEditText=(EditText) findViewById(R.id.cgpaEditText);
        final TextView numOfCourseDoneTextView = (TextView) findViewById(R.id.numOfCourseDoneTextView);
        final EditText numOfCourseDoneEditText=(EditText) findViewById(R.id.numOfCourseDoneEditText);
        final Button knownButton = (Button) findViewById(R.id.knownButton);
        final Button reCalculateButton = (Button) findViewById(R.id.reCalculateButton);
        final Button submitButton = (Button) findViewById(R.id.submitButton);
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final TextView localDataTextView =findViewById(R.id.localDataTextView);
        localDataTextView.setText(localDataStatus(new String[]{"cgpa","numOfTCourses"},sharedPreferences));



        knownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                knownButtonClicked=!knownButtonClicked;
                //Default:KnownButton not clicked
                float submitButtonTranslation = -(displayMetrics.widthPixels/3);
                float knownButtonTranslation=-400;
                visibility=View.INVISIBLE;//For toggling effect.
                if(knownButtonClicked){
                    knownButtonTranslation=-knownButtonTranslation;
                    submitButtonTranslation=-submitButtonTranslation;
                    visibility=View.VISIBLE;
                    Toast.makeText(PermutationCalc.this, "Click Known Again to Undo", Toast.LENGTH_LONG).show();
                }
                submitButton.animate().translationYBy(knownButtonTranslation);
                knownButton.animate().translationXBy(submitButtonTranslation);;
                //reCalculateButton will disappear when the user knows his/her cgpa.
                reCalculateButton.setVisibility(visibility==View.VISIBLE?View.INVISIBLE:View.VISIBLE);
                numOfCourseDoneTextView.setVisibility(visibility);
                numOfCourseDoneEditText.setVisibility(visibility);
                cgpaTextView.setVisibility(visibility);
                cgpaEditText.setVisibility(visibility);
            }
        });

        reCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermutationCalc.this, GPACalc.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numOfTBTCourses=-1;
                float gpaWanted=-1;
                    try{
                        if(knownButtonClicked) {//If Known button has been clicked.
                            editor.putInt("numOfTCourses", Integer.parseInt(((EditText) findViewById(R.id.numOfCourseDoneEditText))
                                    .getText().toString()));
                            editor.putFloat("cgpa", Float.parseFloat(((EditText) findViewById(R.id.cgpaEditText))
                                    .getText().toString()));
                            editor.apply();
                        }
                        numOfTBTCourses = Integer.parseInt(((EditText) findViewById(R.id.numOfTBTCourseEditText)).getText().toString());
                        gpaWanted = Float.parseFloat(((EditText) findViewById(R.id.gpaWantedEditText)).getText().toString());
                    }catch(NumberFormatException n){
                        Toast.makeText(PermutationCalc.this,"Wrong input ",Toast.LENGTH_LONG).show();
                    }
                boolean errorFree=numOfTBTCourses!=-1&&gpaWanted!=-1;
                if(errorFree){
                    Toast.makeText(PermutationCalc.this,"Got the Permutations",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PermutationCalc.this,PermutationResults.class);
                    intent.putExtra("numOfTBTCourses",numOfTBTCourses);
                    intent.putExtra("gpaWanted",gpaWanted);
                    startActivity(intent);}
            }
        });
    }

    /*Once An Inner activity is closed & given that this is the parent activity of the inner activity
    Update the display for the LocalData*/
    protected void onResume() {
        super.onResume();
        final SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        final TextView localDataTextView =findViewById(R.id.localDataTextView);
        localDataTextView.setText(localDataStatus(new String[]{"cgpa","numOfTCourses"},sharedPreferences));
    };
}
