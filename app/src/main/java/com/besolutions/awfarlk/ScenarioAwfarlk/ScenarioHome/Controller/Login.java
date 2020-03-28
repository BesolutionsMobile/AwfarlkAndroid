package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.awfarlk.NetworkLayer.Apicalls;
import com.besolutions.awfarlk.NetworkLayer.NetworkInterface;
import com.besolutions.awfarlk.NetworkLayer.ResponseModel;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Model.ModelLogin;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Model.ModelUserDatum;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.local_data.send_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity implements NetworkInterface {
    TextView txtforgetpassword,txtdonthaveaccount;
    Button btnlogin;
    EditText editmail,editpassword;
    LinearLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loading = findViewById(R.id.loading);
        txtdonthaveaccount = findViewById(R.id.txtDontHaveAccount);
        txtforgetpassword = findViewById(R.id.txtForgotPassword);
        btnlogin = findViewById(R.id.btnLogin);
        editmail = findViewById(R.id.editEmailLogin);
        editpassword = findViewById(R.id.editPasswordLogin);

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

                if (editmail.getText().toString().equals("")){

                    editmail.setError("Please Enter Your Email Address !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editEmailLogin));

                    Toasty.error(Login.this, "Please Enter Your Email Address !!", Toast.LENGTH_SHORT).show();



                }else if (editpassword.getText().toString().equals("")){

                    editpassword.setError("Please Enter Your Password !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editPasswordLogin));

                    Toasty.error(Login.this, "Please Enter Your Password !!", Toast.LENGTH_SHORT).show();


                }else {

                    loading.setVisibility(View.VISIBLE);
                    new Apicalls(Login.this,Login.this).loginUser(editmail.getText().toString(),editpassword.getText().toString());


                }




            }
        });
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        loading.setVisibility(View.GONE);
        Gson gson = new Gson();

        send_data send_data = new send_data();
        send_data.userId_check(this,true);
        ModelLogin login = gson.fromJson(model.getResponse(),ModelLogin.class);
        ModelUserDatum userDatum = login.getUserData();
        if (login.getStatus() == 1){

            send_data.SET_USER_NAME(this,userDatum.getName());
            send_data.SET_USER_EMAIL(this,userDatum.getMail());
            send_data.SET_USER_PHONE(this,userDatum.getPhone());
            send_data.SET_USER_ID(this,userDatum.getId());


            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }else if (login.getStatus() == 2)
        {

            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.editEmailLogin));

            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.editPasswordLogin));

            editmail.setError("Please Recheck Your Email ");

            editpassword.setError("Please Recheck Your Password");

            Toasty.error(this, "Wrong Email Or Password Please Recheck And Try Again ", Toast.LENGTH_SHORT).show();

        }else
        {
            Toasty.error(this, ""+login.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);
        Log.e("error",error.toString());

    }
}
