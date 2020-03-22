package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.besolutions.awfarlk.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), Home_Activity.class));
                finish();

            }
        };
        new Timer().schedule(timerTask, 4000);
    }


    @Override
    protected void onResume() {

        super.onResume();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), Home_Activity.class));
                finish();

            }
        };
        new Timer().schedule(timerTask, 4000);
    }
}
