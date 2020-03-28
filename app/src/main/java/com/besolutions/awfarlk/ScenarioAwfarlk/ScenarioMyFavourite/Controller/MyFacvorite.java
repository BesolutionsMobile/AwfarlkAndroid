package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.ModelCatrgory;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Model.ModelAllFavourite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Model.ModelProduct;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Model.model_MyFavourite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Pattrens.RcyMyFavouriteAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioPersonalInfo.Controller.PersonalInfo;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelSubItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Pattrens.RcySubItemAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.ScenarioAwfarlk.Search_Popup;
import com.besolutions.awfarlk.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class MyFacvorite extends AppCompatActivity implements NetworkInterface {
    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;
    TextView txtpagenametoolbar;
    ImageView imgsearchtoolbar,imgbacktoolbar;
    ModelProduct[]  products;
    List<ModelProduct> productList = new ArrayList<>();
    public static LinearLayout loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_facvorite);

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


                startActivity(new Intent(MyFacvorite.this, Cart.class));

            }
        });
        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        txtpagenametoolbar.setText("My Favourite");
        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        loading = findViewById(R.id.loading);
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MyFacvorite.this, MainActivity.class));
            }
        });


        String userid = saved_data.get_user_id(MyFacvorite.this);
        loading.setVisibility(View.VISIBLE);
        new Apicalls(MyFacvorite.this,MyFacvorite.this).get_all_favourite_product(userid);



        imgsearchtoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Search_Popup search_popup = new Search_Popup();
                search_popup.dialog(MyFacvorite.this, R.layout.search_popup, 1);


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
        ModelAllFavourite allFavourite = gson.fromJson(model.getResponse(),ModelAllFavourite.class);
        products = allFavourite.getProducts();
        if (allFavourite.getStatus() == 1){

            for (int i=0;i<products.length;i++){

                ModelProduct product = new ModelProduct();

                product.setDiscount(products[i].getDiscount());
                product.setFavorite(products[i].getFavorite());
                product.setId(products[i].getId());
                product.setImage(products[i].getImage());
                product.setPriceAfterDiscount(products[i].getPriceAfterDiscount());
                product.setPriceBeforeDiscount(products[i].getPriceBeforeDiscount());
                product.setRating(products[i].getRating());
                product.setTitle(products[i].getTitle());

                productList.add(product);

            }

            RecyclerView recyclerView = findViewById(R.id.rcyMyFavourite);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            RcyMyFavouriteAdapter adabter = new RcyMyFavouriteAdapter(productList,this);
            recyclerView.setAdapter(adabter);



        }else if (allFavourite.getStatus() == 2){
            Gson gson1 = new Gson();
            ModelChangePassword changePassword = gson1.fromJson(model.getResponse(), ModelChangePassword.class);
            Toasty.error(MyFacvorite.this, "" + changePassword.getMessage(), Toast.LENGTH_SHORT).show();



        }else {

            Gson gson1 = new Gson();
            ModelChangePassword changePassword = gson1.fromJson(model.getResponse(), ModelChangePassword.class);
            Toasty.error(MyFacvorite.this, "" + changePassword.getMessage(), Toast.LENGTH_SHORT).show();


        }


    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);
        Log.e("eroor",error.toString());

    }
}
