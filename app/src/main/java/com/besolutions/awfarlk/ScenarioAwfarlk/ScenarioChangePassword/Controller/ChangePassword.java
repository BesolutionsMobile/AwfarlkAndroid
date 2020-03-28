package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioChangePassword.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.awfarlk.NetworkLayer.Apicalls;
import com.besolutions.awfarlk.NetworkLayer.NetworkInterface;
import com.besolutions.awfarlk.NetworkLayer.ResponseModel;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioChangePassword.Model.ModelChangePassword;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Controller.Login;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Controller.Register;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.ScenarioAwfarlk.Search_Popup;
import com.besolutions.awfarlk.local_data.saved_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class ChangePassword extends AppCompatActivity implements NetworkInterface {
    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;
    TextView txtpagenametoolbar;
    ImageView imgsearchtoolbar,imgbacktoolbar;

    EditText editoldpassword,editnewpassword,editrenewpasssword;
    Button btnchangepassword;
    LinearLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Realm.init(this);
        realm_adapter_cart = new Realm_adapter_Cart(realm);
        cartModels = realm_adapter_cart.retrieve();
        txtcartcounter = findViewById(R.id.txtCartCounter);
        imagecart = findViewById(R.id.imgCart);
        if (cartModels.size() == 0) {
            txtcartcounter.setVisibility(View.GONE);
        } else {
            txtcartcounter.setVisibility(View.VISIBLE);
            txtcartcounter.setText("" + cartModels.size());
        }
        imagecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(ChangePassword.this, Cart.class));

            }
        });
        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        txtpagenametoolbar.setText("About Us");
        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        editoldpassword = findViewById(R.id.editOldPassword);
        editnewpassword = findViewById(R.id.editNewPassword);
        editrenewpasssword = findViewById(R.id.editReNewPassword);
        btnchangepassword = findViewById(R.id.btnChangePassword);
        loading = findViewById(R.id.loading);

        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ChangePassword.this, MainActivity.class));
            }
        });
        imgsearchtoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Search_Popup search_popup = new Search_Popup();
                search_popup.dialog(ChangePassword.this, R.layout.search_popup, 1);


            }
        });


        btnchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editoldpassword.getText().toString().equals("")){
                    editoldpassword.setError("Please Enter Your Old Password !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editOldPassword));

                    Toasty.error(ChangePassword.this, "Please Enter Your Old Password!!", Toast.LENGTH_SHORT).show();

                }else if (editnewpassword.getText().toString().equals("")){

                    editnewpassword.setError("Please Enter Your New Password !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editNewPassword));

                    Toasty.error(ChangePassword.this, "Please Enter Your New Password!!", Toast.LENGTH_SHORT).show();



                }else  if (editrenewpasssword.getText().toString().equals("")){


                    editrenewpasssword.setError("Please Enter Your Confirmation Password !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editReNewPassword));

                    Toasty.error(ChangePassword.this, "Please Enter Your Confirmation Password!!", Toast.LENGTH_SHORT).show();



                }else if (!editnewpassword.getText().toString().equals(editrenewpasssword.getText().toString())){

                    editnewpassword.setError("Sorry Your Passwords Dosnt Match");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editNewPassword));

                    editrenewpasssword.setError("Sorry Your Passwords Dosnt Match");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editReNewPassword));

                    Toasty.error(ChangePassword.this, "Please Enter Your Passwords Correctly", Toast.LENGTH_SHORT).show();

                }else {

                    loading.setVisibility(View.VISIBLE);
                    String userid = saved_data.get_user_id(ChangePassword.this);
                    new Apicalls(ChangePassword.this,ChangePassword.this).change_password(editoldpassword.getText().toString(),editnewpassword.getText().toString(),editrenewpasssword.getText().toString(),userid);

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
        ModelChangePassword changePassword =gson.fromJson(model.getResponse(),ModelChangePassword.class);
        if (changePassword.getStatus() == 1){

            Toasty.success(ChangePassword.this, ""+changePassword.getMessage(), Toast.LENGTH_SHORT).show();


        }else if (changePassword.getStatus()==2){

            Toasty.error(ChangePassword.this, ""+changePassword.getMessage(), Toast.LENGTH_SHORT).show();

        }else {

            Toasty.error(ChangePassword.this, ""+changePassword.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);
    }
}
