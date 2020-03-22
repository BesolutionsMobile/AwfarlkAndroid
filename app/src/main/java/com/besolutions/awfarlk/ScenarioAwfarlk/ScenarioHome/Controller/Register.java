package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.R;

public class Register extends AppCompatActivity {
    TextView txtgotologin;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtgotologin = findViewById(R.id.txtGoToLogin);
        btnlogin = findViewById(R.id.btnLogIn);

        txtgotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Register.this,Login.class));
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });
    }
}
