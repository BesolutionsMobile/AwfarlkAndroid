package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioٍSearchResult.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSubCategory.Controller.Sub_Category;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelProduct;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelSubItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Pattrens.RcySubItemAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioٍSearchResult.Model.ModelSearchProduct;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioٍSearchResult.Model.ModelSearchResult;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioٍSearchResult.Pattrens.RcySearchAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.Search_Popup;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.besolutions.awfarlk.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class SearchResult extends AppCompatActivity implements NetworkInterface {


    ImageView imgsearchtoolbar,imgbacktoolbar;
    TextView txtpagenametoolbar;
    TinyDB tinyDB;
    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;
    public static LinearLayout loading;
    int x =0;
    List<ModelSearchProduct> productList = new ArrayList<>();
    ModelSearchProduct[] products;
    String search_text;
    TextView txtnorearch;
    LinearLayout linearserachresult;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);


        Realm.init(this);
        realm_adapter_cart = new Realm_adapter_Cart(realm);
        cartModels = realm_adapter_cart.retrieve();
        txtcartcounter = findViewById(R.id.txtCartCounter);
        imagecart = findViewById(R.id.imgCart);
        txtnorearch = findViewById(R.id.txtNoSearch);
        linearserachresult = findViewById(R.id.linearSearchResult);

        if (cartModels.size() == 0) {
            txtcartcounter.setVisibility(View.GONE);
        } else {
            txtcartcounter.setVisibility(View.VISIBLE);
            txtcartcounter.setText("" + cartModels.size());
        }
        imagecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(SearchResult.this, Cart.class));

            }
        });
        tinyDB = new TinyDB(this);
        recyclerView = findViewById(R.id.rcySubItem);
        loading = findViewById(R.id.loading);
        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SearchResult.this, MainActivity.class));
            }
        });

        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        txtpagenametoolbar.setText(tinyDB.getString("PageSubName"));


        String userId = saved_data.get_user_id(this);
        String subCategoreyId = tinyDB.getString("SubCategoryId");
        Log.e("subId",subCategoreyId);

        search_text = tinyDB.getString("searchtext");
        String user_id = saved_data.get_user_id(this);

        search_text = search_text.replaceAll(" " ,  "%20");
        loading.setVisibility(View.VISIBLE);
        new Apicalls(SearchResult.this,SearchResult.this).get_search(search_text,user_id);



        imgsearchtoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Search_Popup search_popup = new Search_Popup();
                search_popup.dialog(SearchResult.this, R.layout.search_popup, 1);


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
        ModelSearchResult modelSearchResult = gson.fromJson(model.getResponse(),ModelSearchResult.class);

        if (modelSearchResult.getStatus() == 1){
            if (modelSearchResult.getProducts() == null){

                linearserachresult.setVisibility(View.GONE);
                txtnorearch.setVisibility(View.VISIBLE);

            }else {
                products = modelSearchResult.getProducts();


                for (int i=0; i<products.length; i++){

                    ModelSearchProduct product = new ModelSearchProduct();

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


                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(this,2));
                RcySearchAdapter adabter = new RcySearchAdapter(productList,this);
                recyclerView.setAdapter(adabter);

                DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL);
                Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.vertical_divider);
                verticalDecoration.setDrawable(verticalDivider);
                recyclerView.addItemDecoration(verticalDecoration);



            }





        }else if (modelSearchResult.getStatus() == 2){


        }else {



        }

    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SearchResult.this, MainActivity.class));
        finish();

    }
}
