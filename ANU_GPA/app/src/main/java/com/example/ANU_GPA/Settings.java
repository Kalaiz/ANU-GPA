package com.example.ANU_GPA;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

/*Authorship: Prateek Arora (u6742441)*/

public class Settings extends AppCompatActivity {
    ScaleEffect effect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Switch animationSwitch=findViewById(R.id.animationSwitch);
        final SharedPreferences dataSharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        LinearLayout passwordLinearLayout = findViewById(R.id.passwordLinearLayout);
        LinearLayout feedbackLinearLayout = findViewById(R.id.feedbackLinearLayout);
        LinearLayout updateLinearLayout = findViewById(R.id.updateLinearLayout);
        LinearLayout aboutLinearLayout = findViewById(R.id.aboutLinearLayout);
        final LinearLayout[] layouts=new LinearLayout[]{passwordLinearLayout,feedbackLinearLayout,updateLinearLayout,aboutLinearLayout};
        effect=new ScaleEffect(layouts);
        if(dataSharedPreferences.getBoolean("animation",true)){
            animationSwitch.setChecked(true);
            }
        else{effect.setAnimationEnd(true);
            animationSwitch.setChecked(false);}

        passwordLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences pass = getSharedPreferences("com.example.ANU_GPA.Passwords", MODE_PRIVATE);
                if(pass.getString("password", "").equals("")){
                    Intent intent = new Intent(Settings.this,PasswordSet.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Settings.this,Password.class);
                    startActivity(intent);
                }

            }
        });

        feedbackLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Settings.this, GiveFeedback.class);
                startActivity(intent);

            }
        });

        updateLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Settings.this, FontSize.class);
                startActivity(intent);

            }
        });

        aboutLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this,About.class);
                startActivity(intent);
            }
        });



        animationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SharedPreferences.Editor editor = dataSharedPreferences.edit();
                    if(isChecked){
                        editor.putBoolean("animation",true);
                        effect=new ScaleEffect(layouts);
                    }
                    else{
                        editor.putBoolean("animation",false);
                        effect.setAnimationEnd(true);

                    }
                    editor.apply();
            }
        });




    }
}
