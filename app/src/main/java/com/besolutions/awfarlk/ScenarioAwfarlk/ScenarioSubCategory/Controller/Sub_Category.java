package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSubCategory.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.besolutions.awfarlk.NetworkLayer.Apicalls;
import com.besolutions.awfarlk.NetworkLayer.NetworkInterface;
import com.besolutions.awfarlk.NetworkLayer.ResponseModel;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.ModelAllGategorey;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.ModelCatrgory;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.Model_Main_Rcy;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Pattrens.RcyMainGridAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSubCategory.Pattrens.RcySubCategoryAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.ScenarioAwfarlk.Search_Popup;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Sub_Category extends AppCompatActivity implements NetworkInterface {

    ImageView imgsearchtoolbar, imgbacktoolbar, imgcarttoolbar;
    RelativeLayout relativecarttoolbar;
    TextView txtpagenametoolbar, txtcartcountertoolbar;

    TinyDB tinyDB;

    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;

    List<ModelCatrgory> catrgoryList = new ArrayList<>();
    ModelCatrgory[] catrgories;
    LinearLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        Realm.init(this);
        realm_adapter_cart = new Realm_adapter_Cart(realm);
        cartModels = realm_adapter_cart.retrieve();
        txtcartcounter = findViewById(R.id.txtCartCounter);
        loading = findViewById(R.id.loading);
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


                startActivity(new Intent(Sub_Category.this, Cart.class));

            }
        });

        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        relativecarttoolbar = findViewById(R.id.relativeCartToolbar);
        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        tinyDB = new TinyDB(this);

        txtpagenametoolbar.setText(tinyDB.getString("PageName"));
        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Sub_Category.this, MainActivity.class));
            }
        });

        String cateoreyId = tinyDB.getString("CategoreId");



        loading.setVisibility(View.VISIBLE);
        new Apicalls(Sub_Category.this, Sub_Category.this).get_all_sub_category(cateoreyId);

        imgsearchtoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Search_Popup search_popup = new Search_Popup();
                search_popup.dialog(Sub_Category.this, R.layout.search_popup, 1);


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
        ModelAllGategorey allGategorey = gson.fromJson(model.getResponse(), ModelAllGategorey.class);
        catrgories = allGategorey.getCatrgories();

        for (int i = 0; i < catrgories.length; i++){

            ModelCatrgory catrgory = new ModelCatrgory();

            catrgory.setAdv(catrgories[i].getAdv());
            catrgory.setId(catrgories[i].getId());
            catrgory.setImage(catrgories[i].getImage());
            catrgory.setName(catrgories[i].getName());

            catrgoryList.add(catrgory);

        }

        RecyclerView recyclerView = findViewById(R.id.rcyMain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        RcySubCategoryAdapter adabter = new RcySubCategoryAdapter(catrgoryList, this);
        recyclerView.setAdapter(adabter);

        DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.HORIZONTAL);
        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        recyclerView.addItemDecoration(verticalDecoration);


    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);

    }
}
