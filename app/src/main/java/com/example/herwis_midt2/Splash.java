package com.example.herwis_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                // do something here
                finish();
                startActivity(new Intent(Splash.this,MainActivity.class));
            }
        };
        Timer opening=new Timer();
        opening.schedule(task,6000);
    }
}