package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioPersonalInfo.Controller;

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
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioChangePassword.Controller.ChangePassword;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioChangePassword.Model.ModelChangePassword;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioPersonalInfo.Model.ModelUser;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioPersonalInfo.Model.ModelViewProfile;
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

public class PersonalInfo extends AppCompatActivity implements NetworkInterface {
    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;
    TextView txtpagenametoolbar;
    ImageView imgsearchtoolbar, imgbacktoolbar;

    LinearLayout loading;
    EditText editname, editemail, editphone;
    Button btneditprofile;
    int profile = 0;
    ModelUser user;
    TextView changepassword;
    ImageView imgprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
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


                startActivity(new Intent(PersonalInfo.this, Cart.class));

            }
        });

        changepassword = findViewById(R.id.txtChangePassword);


        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(PersonalInfo.this, ChangePassword.class));

            }
        });

        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        txtpagenametoolbar.setText("Personal Info");
        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        editemail = findViewById(R.id.editEmailProfile);
        editname = findViewById(R.id.editNameProfile);
        editphone = findViewById(R.id.editPhoneProfile);
        loading = findViewById(R.id.loading);
        btneditprofile = findViewById(R.id.btnEditProfile);
        imgprofile =findViewById(R.id.imgPersonalInfo);
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(PersonalInfo.this, MainActivity.class));
            }
        });

        String userid = saved_data.get_user_id(PersonalInfo.this);
        loading.setVisibility(View.VISIBLE);
        new Apicalls(PersonalInfo.this, PersonalInfo.this).view_profile(userid);
        profile = 1;

        btneditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editname.getText().toString().equals("")) {
                    editname.setError("Please Enter Your Name !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editNameProfile));

                    Toasty.error(PersonalInfo.this, "Please Enter Your Name !!", Toast.LENGTH_SHORT).show();

                } else if (editemail.getText().toString().equals("")) {

                    editemail.setError("Please Enter Your New Email Address!!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editEmailProfile));

                    Toasty.error(PersonalInfo.this, "Please Enter Your Email Address !!", Toast.LENGTH_SHORT).show();


                } else if (editphone.getText().toString().equals("")) {


                    editphone.setError("Please Enter Your Phone Number !!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editPhoneProfile));

                    Toasty.error(PersonalInfo.this, "Please Enter Your Phone Number!!", Toast.LENGTH_SHORT).show();

                } else {

                    loading.setVisibility(View.VISIBLE);
                    String userid = saved_data.get_user_id(PersonalInfo.this);
                    new Apicalls(PersonalInfo.this, PersonalInfo.this).edit_profile(editname.getText().toString(), editemail.getText().toString(), editphone.getText().toString(), userid);
                    profile = 2;
                }

            }
        });

        imgsearchtoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Search_Popup search_popup = new Search_Popup();
                search_popup.dialog(PersonalInfo.this, R.layout.search_popup, 1);


            }
        });

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        loading.setVisibility(View.GONE);

        if (profile == 1) {
            profile = 0;

            Gson gson = new Gson();
            ModelViewProfile viewProfile = gson.fromJson(model.getResponse(), ModelViewProfile.class);
            if (viewProfile.getStatus() == 1) {

                user = viewProfile.getUser();

                editphone.setText(user.getPhone());
                editname.setText(user.getName());
                editemail.setText(user.getMail());



            } else if (viewProfile.getStatus() == 2) {

                Gson gson1 = new Gson();
                ModelChangePassword changePassword = gson1.fromJson(model.getResponse(), ModelChangePassword.class);
                Toasty.error(PersonalInfo.this, "" + changePassword.getMessage(), Toast.LENGTH_SHORT).show();


            } else {

                Gson gson2 = new Gson();
                ModelChangePassword changePassword = gson2.fromJson(model.getResponse(), ModelChangePassword.class);
                Toasty.error(PersonalInfo.this, "" + changePassword.getMessage(), Toast.LENGTH_SHORT).show();


            }


        } else if (profile == 2) {
            profile = 0;

            Gson gson = new Gson();
            ModelChangePassword changePassword = gson.fromJson(model.getResponse(), ModelChangePassword.class);
            if (changePassword.getStatus() == 1) {

                Toasty.success(PersonalInfo.this, "" + changePassword.getMessage(), Toast.LENGTH_SHORT).show();


            } else if (changePassword.getStatus() == 2) {

                Toasty.error(PersonalInfo.this, "" + changePassword.getMessage(), Toast.LENGTH_SHORT).show();


            } else {

                Toasty.error(PersonalInfo.this, "" + changePassword.getMessage(), Toast.LENGTH_SHORT).show();


            }

        }
    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);

    }
}
