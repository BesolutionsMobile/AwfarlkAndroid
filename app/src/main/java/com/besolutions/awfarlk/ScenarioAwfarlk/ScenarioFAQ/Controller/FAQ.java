package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Model.FAQ_Group1_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Model.ModelFAQ;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Model.ModelQuestion;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Pattrens.FAQ_Group1_Adapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.ModelCatrgory;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioPersonalInfo.Controller.PersonalInfo;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.ScenarioAwfarlk.Search_Popup;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class FAQ extends AppCompatActivity implements NetworkInterface {
    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;
    TextView txtpagenametoolbar;
    ImageView imgsearchtoolbar, imgbacktoolbar;
    List<ModelQuestion> faqlist = new ArrayList<>();
    ModelQuestion[] questions;
    LinearLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
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


                startActivity(new Intent(FAQ.this, Cart.class));

            }
        });
        loading = findViewById(R.id.loading);
        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        txtpagenametoolbar.setText("FAQ");
        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FAQ.this, MainActivity.class));
            }
        });

        loading.setVisibility(View.VISIBLE);
        new Apicalls(FAQ.this,FAQ.this).faq_question();

        imgsearchtoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Search_Popup search_popup = new Search_Popup();
                search_popup.dialog(FAQ.this, R.layout.search_popup, 1);


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
        ModelFAQ faq = gson.fromJson(model.getResponse(), ModelFAQ.class);
        questions = faq.getQuestions();

        if (faq.getStatus() == 1) {

            for (int i=0;i<questions.length;i++){

                ModelQuestion question = new ModelQuestion();

                question.setDescription(questions[i].getDescription());
                question.setId(questions[i].getId());
                question.setTitle(questions[i].getTitle());

                faqlist.add(question);
            }

            //Group1
            RecyclerView recyclerView1 = findViewById(R.id.rcyFaqGroup1);
            recyclerView1.setLayoutManager(new LinearLayoutManager(this));
            recyclerView1.setAdapter(new FAQ_Group1_Adapter(recyclerView1, faqlist));



        } else if (faq.getStatus() == 2) {

            Gson gson1 = new Gson();
            ModelChangePassword changePassword = gson1.fromJson(model.getResponse(), ModelChangePassword.class);
            Toasty.error(FAQ.this, "" + changePassword.getMessage(), Toast.LENGTH_SHORT).show();

        } else {

            Gson gson1 = new Gson();
            ModelChangePassword changePassword = gson1.fromJson(model.getResponse(), ModelChangePassword.class);
            Toasty.error(FAQ.this, "" + changePassword.getMessage(), Toast.LENGTH_SHORT).show();


        }


    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);

    }
}
