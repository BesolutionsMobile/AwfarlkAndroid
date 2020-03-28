package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller;

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
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.awfarlk.NetworkLayer.Apicalls;
import com.besolutions.awfarlk.NetworkLayer.NetworkInterface;
import com.besolutions.awfarlk.NetworkLayer.ResponseModel;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.ModelCatrgory;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.Model_Main_Rcy;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Pattrens.RcyMainGridAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSubCategory.Controller.Sub_Category;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelProduct;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelProduct_SubCategory;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelSubItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Pattrens.RcySubItemAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.ScenarioAwfarlk.Search_Popup;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.besolutions.awfarlk.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Sub_Item extends AppCompatActivity implements NetworkInterface {

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
    List<ModelProduct> productList = new ArrayList<>();
    ModelProduct[] products;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_item);
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


                startActivity(new Intent(Sub_Item.this, Cart.class));

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

                startActivity(new Intent(Sub_Item.this, Sub_Category.class));
            }
        });

        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        txtpagenametoolbar.setText(tinyDB.getString("PageSubName"));

        loading.setVisibility(View.VISIBLE);
        String userId = saved_data.get_user_id(this);
        String subCategoreyId = tinyDB.getString("SubCategoryId");
        Log.e("subId",subCategoreyId);

        new Apicalls(Sub_Item.this,Sub_Item.this).get_all_product_off_sub_category(subCategoreyId,userId,"");


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy>0){

                    if (x==0){

                        Log.e("لا توجد بيانات", String.valueOf(x));

                    }else {

                        get_pagination_sub_Categorey(String.valueOf(x));

                    }


                }



            }
        });



        List<ModelSubItem> subItemList = new ArrayList<>();

        int imageItemMain[] ={R.drawable.aircondition,R.drawable.aircondition, R.drawable.aircondition,
                R.drawable.aircondition,R.drawable.aircondition,R.drawable.aircondition,R.drawable.aircondition,
                R.drawable.aircondition,R.drawable.aircondition, R.drawable.aircondition};


        String textTitleSubItem[] ={"تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)","تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)",
                "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)"};

        String textdiscoutSubItem[] ={"20", "10", "5", "25", "35","40",
                "90", "23", "14", "10"};

        String textPriceSubItem[] ={"1220", "1880", "2565", "2635", "3635","4220",
                "6890", "2648", "2614", "5650"};

        float ratingSubItem[] ={1.255f, 3.555f, 2.255f, 4.255f,1.56f,2.895f,
                2.995f, 2.678f,2.345f , 3.45f};

        for (int i = 0; i<imageItemMain.length; i++)
        {
            ModelSubItem modelSubItem = new ModelSubItem(textTitleSubItem[i],textdiscoutSubItem[i],textPriceSubItem[i],imageItemMain[i],ratingSubItem[i]);
            subItemList.add(modelSubItem);
        }


        imgsearchtoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Search_Popup search_popup = new Search_Popup();
                search_popup.dialog(Sub_Item.this, R.layout.search_popup, 1);


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
        ModelProduct_SubCategory product_subCategory = gson.fromJson(model.getResponse(),ModelProduct_SubCategory.class);
        products = product_subCategory.getProducts();
        if (product_subCategory.getStatus() == 1){
            x = x+1;

            for (int i=0; i<products.length; i++){

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


            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            RcySubItemAdapter adabter = new RcySubItemAdapter(productList,this);
            recyclerView.setAdapter(adabter);

            DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    DividerItemDecoration.VERTICAL);
            Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.vertical_divider);
            verticalDecoration.setDrawable(verticalDivider);
            recyclerView.addItemDecoration(verticalDecoration);




        }else if (product_subCategory.getStatus() == 2){

            x =0;


        }else {
            x=0;

        }



    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);

    }

    private void get_pagination_sub_Categorey(String pageNumber){
        loading.setVisibility(View.VISIBLE);
        String userId = saved_data.get_user_id(this);
        String subCategoreyId = tinyDB.getString("SubCategoryId");

        new Apicalls(Sub_Item.this,Sub_Item.this).get_all_product_off_sub_category(subCategoreyId,userId,pageNumber);

    }


}
