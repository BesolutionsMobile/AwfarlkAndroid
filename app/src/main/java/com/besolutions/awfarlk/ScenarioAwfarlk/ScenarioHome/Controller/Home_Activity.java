package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.besolutions.awfarlk.R;

public class Home_Activity extends AppCompatActivity {

    Button btncreate,btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btncreate = findViewById(R.id.btnCreateAccount);
        btnlogin = findViewById(R.id.btnLogin);

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Activity.this,Register.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home_Activity.this,Login.class));
            }
        });
    }
}
