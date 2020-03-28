package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.awfarlk.NetworkLayer.Apicalls;
import com.besolutions.awfarlk.NetworkLayer.NetworkInterface;
import com.besolutions.awfarlk.NetworkLayer.ResponseModel;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Model.ModelRegister;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.local_data.send_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class Register extends AppCompatActivity implements NetworkInterface {
    TextView txtgotologin;
    Button btnlogin;
    EditText editname,editphone,editemail,editpassword,editconpassword;
    CheckBox checkBox;
    LinearLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtgotologin = findViewById(R.id.txtGoToLogin);
        btnlogin = findViewById(R.id.btnLogIn);
        loading = findViewById(R.id.loading);
        editname = findViewById(R.id.editNameRegister);
        editemail = findViewById(R.id.editEmailRegister);
        editpassword = findViewById(R.id.editPasswordRegister);
        editphone = findViewById(R.id.editPhoneRegister);
        editconpassword = findViewById(R.id.editConPasswordRegister);


        txtgotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Register.this,Login.class));
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editname.getText().toString().equals("")){

                    editname.setError("Please Enter Your Name !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editNameRegister));

                    Toasty.error(Register.this, "Please Enter Your Name !!", Toast.LENGTH_SHORT).show();



                }else if (editemail.getText().toString().equals("")){

                    editemail.setError("Please Enter Your Email Address !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editEmailRegister));

                    Toasty.error(Register.this, "Please Enter Your Email Address !!", Toast.LENGTH_SHORT).show();


                }else if (editphone.getText().toString().equals("")){

                    editphone.setError("Please Enter Your Phone Number !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editPhoneRegister));

                    Toasty.error(Register.this, "Please Enter Your Phone Number !!", Toast.LENGTH_SHORT).show();




                }else if (editpassword.getText().toString().equals("")){


                    editpassword.setError("Please Enter Your Password !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editPasswordRegister));

                    Toasty.error(Register.this, "Please Enter Your Password!!", Toast.LENGTH_SHORT).show();



                }else if (editconpassword.getText().toString().equals("")){

                    editconpassword.setError("Please Enter Your Password Confirmation !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editConPasswordRegister));

                    Toasty.error(Register.this, "Please Enter Your Password Confirmation !!", Toast.LENGTH_SHORT).show();


                }else if (!editpassword.getText().toString().equals(editconpassword.getText().toString())){

                    editconpassword.setError("Please Enter Your Password Confirmation !!");
                    editpassword.setError("Sorry Your Password Doswn't Match !!");


                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editPasswordRegister));


                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editConPasswordRegister));

                    Toasty.error(Register.this, "Please Enter Your Password Confirmation !!", Toast.LENGTH_SHORT).show();

                }else {

                    loading.setVisibility(View.VISIBLE);
                    new Apicalls(Register.this,Register.this).registerUser(editname.getText().toString(),editemail.getText().toString(),editphone.getText().toString(),editpassword.getText().toString());

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
        ModelRegister register = gson.fromJson(model.getResponse(),ModelRegister.class);

         if (register.getStatus() == 1){
             send_data send_data = new send_data();
             send_data.SET_USER_ID(this, register.getIdUser());

             Toasty.success(this, "Register Done Successfully Please Login With Your Account ", Toast.LENGTH_SHORT).show();
             startActivity(new Intent(Register.this, Login.class));
             finish();
         }if (register.getStatus() == 2){


            editemail.setError("This Email Address Is Already Registered Before");

            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.editEmailRegister));


            editphone.setError("This Phone Number Is Already Registered Before");

            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.editPhoneRegister));

            Toasty.error(Register.this, "The Phone Number Or Email Address Is Already Registered Before", Toast.LENGTH_SHORT).show();




        }if (register.getStatus() == 3){

            Toasty.error(this, "" + register.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);
        Log.e("error",error.toString());

    }
}
