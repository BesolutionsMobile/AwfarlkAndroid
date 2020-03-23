package com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart_Popup;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.Model_Main_Rcy;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model.RcyCommentModel;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model.SliderItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Pattrens.Rcy_Product_details_Adapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Pattrens.SliderAdapter;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;
import io.realm.Realm;

public class Product_Details extends AppCompatActivity {

    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;

    RadioRealButtonGroup group1;
    RadioRealButton button1, button2;

    TextView txtproductdetails,txtprice;

    SliderView sliderView;
    private SliderAdapter adapter;
    TinyDB tinyDB;
    TextView txtpagenametoolbar;
    LinearLayout details,comments;
    RecyclerView recyclerView;
    LinearLayout btnaddtocart;
    ImageView imgsearchtoolbar,imgbacktoolbar;


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

        String price = tinyDB.getString("cartPrice");
        txtprice.setText(price);

        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        txtpagenametoolbar.setText("Product Details");
        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Product_Details.this, Sub_Item.class));
            }
        });
        List<RcyCommentModel> commentModelList = new ArrayList<>();

        int imageCommentPhoto[] ={R.drawable.drpicture,R.drawable.drpicture, R.drawable.drpicture,
                R.drawable.drpicture,R.drawable.drpicture,R.drawable.drpicture,R.drawable.drpicture};

        String textCommentName[] ={"alla hassan", "ali ali ", "alla hassan", "ali ali ","alla hassan", "ali ali ",
                "alla hassan"};

        String textComment[] ={"great product ", "thank you for your opinion ", "you welcome", "best product you can buy ","thank you ", "my pleasure ",
                ":)"};

        for (int i = 0; i<imageCommentPhoto.length; i++)
        {
            RcyCommentModel productAdapter = new RcyCommentModel(textCommentName[i],textComment[i],imageCommentPhoto[i]);
            commentModelList.add(productAdapter);
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Rcy_Product_details_Adapter adabter = new Rcy_Product_details_Adapter(commentModelList, this);
        recyclerView.setAdapter(adabter);


        List<SliderItem> sliderItemList = new ArrayList<>();

        int imageItemMain[] ={R.drawable.aircondition,R.drawable.aircondition, R.drawable.aircondition,
                R.drawable.aircondition,R.drawable.aircondition,R.drawable.aircondition,R.drawable.aircondition};

        for (int i = 0; i<imageItemMain.length; i++)
        {
            SliderItem sliderItem = new SliderItem(imageItemMain[i]);
            sliderItemList.add(sliderItem);
        }

        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setIndicatorSelectedColor(Color.YELLOW);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(false);

        adapter = new SliderAdapter(Product_Details.this,sliderItemList);
        sliderView.setSliderAdapter(adapter);



        group1.setOnPositionChangedListener(new RadioRealButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(RadioRealButton button, int currentPosition, int lastPosition) {

                if (currentPosition == 0) {

                    details.setVisibility(View.VISIBLE);
                    comments.setVisibility(View.GONE);

                    Toast.makeText(Product_Details.this, "alla haassan", Toast.LENGTH_SHORT).show();

                } else if (currentPosition == 1) {

                    details.setVisibility(View.GONE);
                    comments.setVisibility(View.VISIBLE);

                    Toast.makeText(Product_Details.this, "abdelkhalek gad fyad", Toast.LENGTH_SHORT).show();

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
