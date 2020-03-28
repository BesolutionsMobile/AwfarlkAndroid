package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.awfarlk.NetworkLayer.Apicalls;
import com.besolutions.awfarlk.NetworkLayer.NetworkInterface;
import com.besolutions.awfarlk.NetworkLayer.ResponseModel;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.Dialog_Anim;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.ModelSendOrder;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Rcy_Cart_Adapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioChangePassword.Model.ModelChangePassword;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioPersonalInfo.Controller.PersonalInfo;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.Search_Popup;
import com.besolutions.awfarlk.local_data.saved_data;
import com.besolutions.awfarlk.local_data.send_data;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class Cart extends AppCompatActivity implements NetworkInterface {

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
    LinearLayout loading;
    String a = "";



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
        loading= findViewById(R.id.loading);
        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Cart.this, MainActivity.class));
            }
        });


        Realm_adapter_Cart adapter = new Realm_adapter_Cart(realm);
        ArrayList<Realm_Cart_Model> cartModel = adapter.retrieve();

        a = "";
        for (int i = 0; i < adapter.retrieve().size(); i++) {

            if (i + 1 == adapter.retrieve().size()) {

                a += "[" + cartModel.get(i).getProduct_id().toString() + "," + cartModel.get(i).getTxtnumberchoose().toString() + "," + cartModel.get(i).getTxtprice().toString() +"]";

            } else {

                a += "[" + cartModel.get(i).getProduct_id().toString() + "," + cartModel.get(i).getTxtnumberchoose().toString() + "," + cartModel.get(i).getTxtprice().toString() +"]" + ",";

            }

        }
        Log.e("allalalalala", "" + "[" + a + "]");


        txtcompleteorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loading.setVisibility(View.VISIBLE);

                String userName = saved_data.get_user_name(Cart.this);
                String Phone = saved_data.get_user_phone(Cart.this);
                String userId = saved_data.get_user_id(Cart.this);

                new Apicalls(Cart.this,Cart.this).sendOrder("[" + a + "]",userName,"oakwdowdokaowdkowkd",Phone,"1","1",userId);


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

        imgsearchtoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Search_Popup search_popup = new Search_Popup();
                search_popup.dialog(Cart.this, R.layout.search_popup, 1);


            }
        });


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

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        loading.setVisibility(View.GONE);
        Gson gson = new Gson();
        ModelSendOrder sendOrder = gson.fromJson(model.getResponse(),ModelSendOrder.class);
        if (sendOrder.getStatus() == 1){

            send_data.id_Order(Cart.this,sendOrder.getIdOrder());
            Toasty.success(Cart.this, ""+sendOrder.getMessage(), Toast.LENGTH_LONG).show();
            Order_Success_Popup order_success_popup = new Order_Success_Popup();
            order_success_popup.dialog(Cart.this,R.layout.order_completed_dialog,1);

            Realm_adapter_Cart adapterCart = new Realm_adapter_Cart(realm);
            adapterCart.delete_all();

        }else if ( sendOrder.getStatus() == 2){

            Gson gson1 = new Gson();
            ModelChangePassword changePassword = gson1.fromJson(model.getResponse(), ModelChangePassword.class);
            Toasty.error(Cart.this, "" + changePassword.getMessage(), Toast.LENGTH_SHORT).show();



        }else {

            Gson gson1 = new Gson();
            ModelChangePassword changePassword = gson1.fromJson(model.getResponse(), ModelChangePassword.class);
            Toasty.error(Cart.this, "" + changePassword.getMessage(), Toast.LENGTH_SHORT).show();



        }


    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);

    }
}
