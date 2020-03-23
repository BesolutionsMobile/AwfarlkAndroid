package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Model.FAQ_Group1_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Pattrens.FAQ_Group1_Adapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class FAQ extends AppCompatActivity {
    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;
    TextView txtpagenametoolbar;
    ImageView imgsearchtoolbar,imgbacktoolbar;

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

        String question[] ={"What 3rd-party-applications", "What 3rd-party-applications What 3rd-party-applications What 3rd-party-applications", "What 3rd-party-applications", "What 3rd-party-applications What 3rd-party-applications What 3rd-party-applications", "What 3rd-party-applications", "What 3rd-party-applications", "What 3rd-party-applications", "What 3rd-party-applications", "What 3rd-party-applications", "What 3rd-party-applications What 3rd-party-applications", "What 3rd-party-applications What 3rd-party-applications", "What 3rd-party-applications", "What 3rd-party-applications", "What 3rd-party-applications", "What 3rd-party-applications"};
        String answer[] ={"Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev", "Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev", "Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev","Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev", "Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev","Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev", "Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev", "Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev","Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev", "Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev","Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev", "Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev", "Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev","Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev","Tiger Connect integrates with a range of cccv systems across a broad spectrum of vev"};

        List<FAQ_Group1_Model> faqGroup1ModelList = new ArrayList<>();
        for (int i = 0; i<question.length; i++)
        {
            FAQ_Group1_Model faq_group1_model = new FAQ_Group1_Model(question[i],answer[i]);
            faqGroup1ModelList.add(faq_group1_model);

        }

        //Group1
        RecyclerView recyclerView1 = findViewById(R.id.rcyFaqGroup1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(new FAQ_Group1_Adapter(recyclerView1,faqGroup1ModelList));

    }
}
