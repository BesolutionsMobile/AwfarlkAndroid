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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.Model_Main_Rcy;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Pattrens.RcyMainGridAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSubCategory.Pattrens.RcySubCategoryAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.Utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Sub_Category extends AppCompatActivity {

    ImageView imgsearchtoolbar,imgbacktoolbar,imgcarttoolbar;
    RelativeLayout relativecarttoolbar;
    TextView txtpagenametoolbar,txtcartcountertoolbar;

    TinyDB tinyDB;

    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
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


        List<Model_Main_Rcy> main_rcyList = new ArrayList<>();

        int imageItemMain[] ={R.drawable.itemimage,R.drawable.itemimage, R.drawable.itemimage,
                R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,
                R.drawable.itemimage,R.drawable.itemimage, R.drawable.itemimage,
                R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage};


        String textItemMain[] ={"اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية","اجهزة كهربائية",
                "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية","اجهزة كهربائية",
                "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية","اجهزة كهربائية"};

        for (int i = 0; i<imageItemMain.length; i++)
        {
            Model_Main_Rcy model_main_rcy = new Model_Main_Rcy(imageItemMain[i],textItemMain[i]);
            main_rcyList.add(model_main_rcy);
        }


        RecyclerView recyclerView = findViewById(R.id.rcyMain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        RcySubCategoryAdapter adabter = new RcySubCategoryAdapter(main_rcyList,this);
        recyclerView.setAdapter(adabter);

        DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.HORIZONTAL);
        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        recyclerView.addItemDecoration(verticalDecoration);



    }
}
