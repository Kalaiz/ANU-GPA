package com.example.gpa_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

public class Permutationlist extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permutationlist);
        Bundle data = getIntent().getExtras();
        Permutation p = (Permutation) data.getParcelable("PermutationObject");
        EditText possibleOutputs =findViewById(R.id.PossiblePermutationEditText);
        for(Object o:p.getPermutation()){
           int[] op=(int[]) o ;
            //possibleOutputs.append(op[0]+ " " +op[1]+ " "+op[2]+ " "+op[3]+ " "+op[4]);
        }

    }
}
