package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.awfarlk.NetworkLayer.Apicalls;
import com.besolutions.awfarlk.NetworkLayer.NetworkInterface;
import com.besolutions.awfarlk.NetworkLayer.ResponseModel;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Model.ModelForgetPassword;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class ForgetPassword extends AppCompatActivity implements NetworkInterface {

    LinearLayout loading;
    Button btnsendmessage;
    EditText editmail;
    TextView txtgotologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        loading =findViewById(R.id.loading);
        btnsendmessage =findViewById(R.id.btnSendMessageForgetPassword);
        editmail =findViewById(R.id.editEmailForgetPassword);
        txtgotologin =findViewById(R.id.txtGoToLogin);

        txtgotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ForgetPassword.this,Login.class));

            }
        });

        btnsendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editmail.getText().toString().equals("")){

                    editmail.setError("Please Enter Your Email Address !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editEmailForgetPassword));

                    Toasty.error(ForgetPassword.this, "Please Enter Your Email Address !!", Toast.LENGTH_SHORT).show();


                }else {

                    loading.setVisibility(View.VISIBLE);
                    new Apicalls(ForgetPassword.this,ForgetPassword.this).forget_password(editmail.getText().toString());

                }


            }
        });

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        loading.setVisibility(View.VISIBLE);
        Gson gson =new Gson();
        ModelForgetPassword forgetPassword = gson.fromJson(model.getResponse(),ModelForgetPassword.class);

        if (forgetPassword.getStatus() == 1){

            Toasty.success(this, "We Send You Your Password. Thank You :) ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ForgetPassword.this,Login.class));
            finish();

        }else if (forgetPassword.getStatus() == 2){

            editmail.setError("Your Email Address Is Wrong Please Try Again");

            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.editEmailForgetPassword));

            Toasty.error(ForgetPassword.this, "Your Email Address Is Wrong Please Try Again", Toast.LENGTH_SHORT).show();


        }else {

            Toasty.error(this, "" + forgetPassword.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.VISIBLE);

        Log.e("error",error.toString());

    }
}
