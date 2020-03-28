package com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart_Popup;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelAddFavourite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model.ProductComment;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model.ProductModel;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model.RcyCommentModel;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model.SliderItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Pattrens.Rcy_Product_details_Adapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Pattrens.SliderAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.Search_Popup;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.besolutions.awfarlk.local_data.saved_data;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;
import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class Product_Details extends AppCompatActivity implements NetworkInterface {

    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;

    RadioRealButtonGroup group1;
    RadioRealButton button1, button2;


    TextView txtproductdetails, txtprice, txtsend;

    SliderView sliderView;
    private SliderAdapter adapter;
    TinyDB tinyDB;
    TextView txtpagenametoolbar;
    LinearLayout details, comments;
    RecyclerView recyclerView;
    LinearLayout btnaddtocart;
    ImageView imgsearchtoolbar, imgbacktoolbar;
    LinearLayout loading;
    int x = 0;
    String[] albums;
    List<SliderItem> sliderItemList = new ArrayList<>();
    ProductComment[] commentss;
    List<ProductComment> commentModelList = new ArrayList<>();
    EditText editcomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

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


                startActivity(new Intent(Product_Details.this, Cart.class));

            }
        });

        tinyDB = new TinyDB(this);

        group1 = findViewById(R.id.radioRealButtonGroup_1);
        button1 = findViewById(R.id.radioButton1);
        button2 = findViewById(R.id.radioButton2);
        txtproductdetails = findViewById(R.id.txtProductDetails);
        details = findViewById(R.id.linearProductDetails);
        comments = findViewById(R.id.linearcomment);
        txtprice = findViewById(R.id.txtPriceSubItem);
        recyclerView = findViewById(R.id.rcyComment);
        btnaddtocart = findViewById(R.id.linearAddToCart);
        sliderView = findViewById(R.id.imageSlider);
        loading = findViewById(R.id.loading);
        txtsend = findViewById(R.id.txtSend);
//        String price = tinyDB.getString("cartPrice");
        editcomment = findViewById(R.id.editComment);

        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);

        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Product_Details.this, Sub_Item.class));
            }
        });

        txtsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 1;
                loading.setVisibility(View.VISIBLE);

                String productdetails_id = tinyDB.getString("idproductdetails");
                String id_user = saved_data.get_user_id(Product_Details.this);
                new Apicalls(Product_Details.this, Product_Details.this).add_comments(editcomment.getText().toString(), productdetails_id, id_user);


            }
        });

        loading.setVisibility(View.VISIBLE);
        String product_id = tinyDB.getString("ProductId");

        new Apicalls(Product_Details.this, Product_Details.this).get_product_details(product_id);


//        List<RcyCommentModel> commentModelList = new ArrayList<>();
//
//        int imageCommentPhoto[] = {R.drawable.drpicture, R.drawable.drpicture, R.drawable.drpicture,
//                R.drawable.drpicture, R.drawable.drpicture, R.drawable.drpicture, R.drawable.drpicture};
//
//        String textCommentName[] = {"alla hassan", "ali ali ", "alla hassan", "ali ali ", "alla hassan", "ali ali ",
//                "alla hassan"};
//
//        String textComment[] = {"great product ", "thank you for your opinion ", "you welcome", "best product you can buy ", "thank you ", "my pleasure ",
//                ":)"};
//
//        for (int i = 0; i < imageCommentPhoto.length; i++) {
//            RcyCommentModel productAdapter = new RcyCommentModel(textCommentName[i], textComment[i], imageCommentPhoto[i]);
//            commentModelList.add(productAdapter);
//        }
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Rcy_Product_details_Adapter adabter = new Rcy_Product_details_Adapter(commentModelList, this);
//        recyclerView.setAdapter(adabter);

//
//        int imageItemMain[] ={R.drawable.aircondition,R.drawable.aircondition, R.drawable.aircondition,
//                R.drawable.aircondition,R.drawable.aircondition,R.drawable.aircondition,R.drawable.aircondition};
//
//        for (int i = 0; i<imageItemMain.length; i++)
//        {
//            SliderItem sliderItem = new SliderItem(imageItemMain[i]);
//            sliderItemList.add(sliderItem);
//        }


        group1.setOnPositionChangedListener(new RadioRealButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(RadioRealButton button, int currentPosition, int lastPosition) {

                if (currentPosition == 0) {

                    details.setVisibility(View.VISIBLE);
                    comments.setVisibility(View.GONE);

//                    Toast.makeText(Product_Details.this, "alla haassan", Toast.LENGTH_SHORT).show();

                } else if (currentPosition == 1) {

                    details.setVisibility(View.GONE);
                    comments.setVisibility(View.VISIBLE);

//                    Toast.makeText(Product_Details.this, "abdelkhalek gad fyad", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cart_Popup cart_popup = new Cart_Popup();
                cart_popup.dialog(Product_Details.this, R.layout.cart_popup, 1);
                cart_popup.txtproductdetails.setText(txtproductdetails.getText().toString());
                cart_popup.txtprice.setText(txtprice.getText().toString());


            }
        });

        imgsearchtoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Search_Popup search_popup = new Search_Popup();
                search_popup.dialog(Product_Details.this, R.layout.search_popup, 1);


            }
        });


    }


    public void setcartcount() {

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


        if (x == 1) {

            Gson gson = new Gson();

            ModelAddFavourite addFavourite = gson.fromJson(model.getResponse(),ModelAddFavourite.class);
            if (addFavourite.getStatus() == 1){

                Toasty.success(this, ""+addFavourite.getMessage(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(Product_Details.this,Product_Details.class));


            }else if (addFavourite.getStatus() == 2){

                Toasty.error(this, ""+addFavourite.getMessage(), Toast.LENGTH_LONG).show();

            }else {

                Toasty.error(this, ""+addFavourite.getMessage(), Toast.LENGTH_LONG).show();


            }




        } else {
            tinyDB = new TinyDB(this);
            Gson gson = new Gson();
            ProductModel productModel = gson.fromJson(model.getResponse(), ProductModel.class);

            if (productModel.getStatus() == 1) {

                if (productModel.getAlbum() != null) {

                    albums = productModel.getAlbum();

                    for (int i = 0; i < albums.length; i++) {

                        SliderItem sliderItem = new SliderItem();
                        sliderItem.setImageUrl(albums[i]);
                        sliderItemList.add(sliderItem);

                    }

                    sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
                    sliderView.setIndicatorSelectedColor(Color.YELLOW);
                    sliderView.setIndicatorUnselectedColor(Color.GRAY);
                    sliderView.setScrollTimeInSec(3);
                    sliderView.setAutoCycle(false);

                    adapter = new SliderAdapter(Product_Details.this, sliderItemList);
                    sliderView.setSliderAdapter(adapter);
                }

                txtpagenametoolbar.setText(productModel.getTitle());
                txtproductdetails.setText(productModel.getDescription());
                txtprice.setText(productModel.getPriceAfterDiscount());
                tinyDB.putString("idproductdetails", productModel.getId());

                if (productModel.getComments() != null) {


                    commentss = productModel.getComments();

                    for (int i = 0; i < commentss.length; i++) {

                        ProductComment comment = new ProductComment();
                        comment.setUserName(commentss[i].getUserName());
                        comment.setComment(commentss[i].getComment());
                        comment.setDate(commentss[i].getDate());
                        comment.setId(commentss[i].getId());
                        comment.setUserPhoto(commentss[i].getUserPhoto());


                        commentModelList.add(comment);
                    }

                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    Rcy_Product_details_Adapter adabter = new Rcy_Product_details_Adapter(commentModelList, this);
                    recyclerView.setAdapter(adabter);


                }


            } else if (productModel.getStatus() == 2) {

                Log.e("status", String.valueOf(productModel.getStatus()));

            } else {

                Log.e("status", String.valueOf(productModel.getStatus()));

            }

        }
    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);

    }


}
