package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller;

import androidx.appcompat.app.AppCompatActivity;
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
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Rcy_Cart_Adapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;

import java.util.ArrayList;

import io.realm.Realm;

public class Cart extends AppCompatActivity {

    Realm realm;
    Realm_adapter_Cart adapter;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter,txttotal;
    LinearLayout linearcart;
    RecyclerView recyclerView;
    TextView txtcompleteorder,txtnodata;
    TextView txtpagenametoolbar;
    ImageView imgsearchtoolbar,imgbacktoolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Realm.init(this);
        adapter = new Realm_adapter_Cart(realm);
        cartModels = adapter.retrieve();
        recyclerView = findViewById(R.id.rcyMain);

        txtnodata = findViewById(R.id.txtNoData);
        linearcart = findViewById(R.id.linearCart);
        txtcompleteorder = findViewById(R.id.txtCompleteYourOrder);
        txttotal = findViewById(R.id.txtToatalCart);
        txtcartcounter = findViewById(R.id.txtCartCounter);
        imagecart = findViewById(R.id.imgCart);
        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        txtpagenametoolbar.setText("About Us");

        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Cart.this, MainActivity.class));
            }
        });
        if (cartModels.size() == 0) {

            txtcartcounter.setVisibility(View.GONE);


        } else {

            txtcartcounter.setVisibility(View.VISIBLE);
            txtcartcounter.setText("" + cartModels.size());

        }


        imagecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Cart.this, Cart.class));

            }
        });

        if (cartModels.size() == 0) {

            linearcart.setVisibility(View.GONE);
            txtnodata.setVisibility(View.VISIBLE);

        } else {

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            Rcy_Cart_Adapter adabter = new Rcy_Cart_Adapter(cartModels, this);
            recyclerView.setAdapter(adabter);
        }
        int toatalprice = 0;
        for (int x=0; x<adapter.retrieve().size();x++){

            int price = Integer.parseInt(cartModels.get(x).getTxtprice());
            int number = Integer.parseInt(cartModels.get(x).getTxtnumberchoose());

            int totalpricebefortax = price * number;

            toatalprice += totalpricebefortax ;
        }

        String total = String.valueOf(toatalprice);
        txttotal.setText(total);

    }


    public void setToatal(final String total){

        txttotal.setText(total);

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
