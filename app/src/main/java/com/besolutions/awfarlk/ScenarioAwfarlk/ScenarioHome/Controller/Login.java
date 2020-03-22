package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.R;

public class Login extends AppCompatActivity {
    TextView txtforgetpassword,txtdonthaveaccount;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtdonthaveaccount = findViewById(R.id.txtDontHaveAccount);
        txtforgetpassword = findViewById(R.id.txtForgotPassword);
        btnlogin = findViewById(R.id.btnLogin);

        txtdonthaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });

        txtforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ForgetPassword.class));
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });
    }
}
