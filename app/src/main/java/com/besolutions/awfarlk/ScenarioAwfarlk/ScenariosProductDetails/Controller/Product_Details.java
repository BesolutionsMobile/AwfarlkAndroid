package com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart_Popup;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.Model_Main_Rcy;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model.RcyCommentModel;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model.SliderItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Pattrens.Rcy_Product_details_Adapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Pattrens.SliderAdapter;
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

public class Product_Details extends AppCompatActivity {



    RadioRealButtonGroup group1;
    RadioRealButton button1, button2;

    SliderView sliderView;
    private SliderAdapter adapter;

    LinearLayout details,comments;
    RecyclerView recyclerView;
    LinearLayout btnaddtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        group1 = findViewById(R.id.radioRealButtonGroup_1);

        button1 = findViewById(R.id.radioButton1);
        button2 = findViewById(R.id.radioButton2);

        details = findViewById(R.id.linearProductDetails);
        comments = findViewById(R.id.linearcomment);

        recyclerView = findViewById(R.id.rcyComment);
        btnaddtocart = findViewById(R.id.linearAddToCart);
        sliderView = findViewById(R.id.imageSlider);

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


            }
        });

    }
}
