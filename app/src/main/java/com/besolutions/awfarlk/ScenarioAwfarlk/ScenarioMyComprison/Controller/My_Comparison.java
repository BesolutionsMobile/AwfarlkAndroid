package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Rcy_Cart_Adapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Model.model_MyComparison;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Pattrens.RcyMyComparisonAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Pattrens.Realm_adapter_MyComparison;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Model.model_MyFavourite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Pattrens.RcyMyFavouriteAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class My_Comparison extends AppCompatActivity {
    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;
    TextView txtpagenametoolbar;
    ImageView imgsearchtoolbar,imgbacktoolbar;
    Button finishcomparison;
    TextView txtnodata;
    LinearLayout linearmycomparison;

    ArrayList<model_MyComparison> myComparisons = new ArrayList<>();
    Realm_adapter_MyComparison realm_adapter_myComparison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comparison);
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


                startActivity(new Intent(My_Comparison.this, Cart.class));

            }
        });

        txtnodata = findViewById(R.id.txtNoData);
        linearmycomparison = findViewById(R.id.linearMyComparison);
        finishcomparison = findViewById(R.id.btnComparison);
        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        txtpagenametoolbar.setText("My Comparison");
        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(My_Comparison.this, MainActivity.class));
            }
        });
        finishcomparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realm_adapter_myComparison = new Realm_adapter_MyComparison(realm);
                realm_adapter_myComparison.delete_all();
                startActivity(new Intent(My_Comparison.this , My_Comparison.class));
            }
        });


        realm_adapter_myComparison = new Realm_adapter_MyComparison(realm);
        myComparisons = realm_adapter_myComparison.retrieve();

//        List<model_MyComparison> myFavourites = new ArrayList<>();
//
//        int imageItemMain[] ={R.drawable.drpicture,R.drawable.drpicture};
//
//
//        String textTitleSubItem[] ={"تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)"};
//
//
//        String textPriceSubItem[] ={"1220", "1880"};
//
//        float ratingSubItem[] ={1.255f, 3.555f};
//
//        for (int i = 0; i<imageItemMain.length; i++)
//        {
//            model_MyComparison modelSubItem = new model_MyComparison(textTitleSubItem[i],textPriceSubItem[i],imageItemMain[i],ratingSubItem[i]);
//            myFavourites.add(modelSubItem);
//        }

        if (myComparisons.size() == 0) {

            linearmycomparison.setVisibility(View.GONE);
            txtnodata.setVisibility(View.VISIBLE);

        } else {

            RecyclerView recyclerView = findViewById(R.id.rcyMyComparison);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            RcyMyComparisonAdapter adabter = new RcyMyComparisonAdapter(myComparisons,this);
            recyclerView.setAdapter(adabter);

        }


    }

    public  void setcartcount (){

        Realm.init(this);
        Realm_adapter_Cart realm_adapter = new Realm_adapter_Cart(realm);
        cartModels = realm_adapter.retrieve();

        if (cartModels.size() == 0) {

            txtcartcounter.setVisibility(View.GONE);


        } else {

            txtcartcounter.setVisibility(View.VISIBLE);
            txtcartcounter.setText("" + cartModels.size());

        }

    }
}
