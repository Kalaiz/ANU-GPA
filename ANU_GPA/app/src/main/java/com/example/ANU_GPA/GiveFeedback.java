package com.example.ANU_GPA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

// Authorship Prateek Arora , u6742441

public class GiveFeedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_feedback);


        RatingBar ratingBar=(RatingBar) findViewById(R.id.ratingBar);
        Button feedBackButton = (Button) findViewById(R.id.submit_feedback);


        feedBackButton .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiveFeedback.this, FeedbackThanks.class);
                startActivity(intent);
            }
        });






        Thread thread = new Thread() {
            @Override
            public void run() {
                clientSender();
            }
        };
        thread.start();





    }


    public void clientSender() {

        String ip = "10.0.2.2"; //server ip address or hostname

        try {
            Socket socket = new Socket(ip, 5005);
            String msg = "I am sending data to server";
            OutputStream connectedSocket = socket.getOutputStream();
            connectedSocket.write(msg.getBytes());
            connectedSocket.close();
            socket.close();
        }
        catch (IOException i){

        }
    }

}
