package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Model.model_MyFavourite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Pattrens.RcyMyFavouriteAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelSubItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Pattrens.RcySubItemAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MyFacvorite extends AppCompatActivity {
    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;
    TextView txtpagenametoolbar;
    ImageView imgsearchtoolbar,imgbacktoolbar;

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
        imgbacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MyFacvorite.this, MainActivity.class));
            }
        });
        List<model_MyFavourite> myFavourites = new ArrayList<>();

        int imageItemMain[] ={R.drawable.drpicture,R.drawable.drpicture, R.drawable.drpicture,
                R.drawable.drpicture,R.drawable.drpicture,R.drawable.drpicture,R.drawable.drpicture,
                R.drawable.drpicture,R.drawable.drpicture, R.drawable.drpicture};


        String textTitleSubItem[] ={"تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)","تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)",
                "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)"};


        String textPriceSubItem[] ={"1220", "1880", "2565", "2635", "3635","4220",
                "6890", "2648", "2614", "5650"};

        float ratingSubItem[] ={1.255f, 3.555f, 2.255f, 4.255f,1.56f,2.895f,
                2.995f, 2.678f,2.345f , 3.45f};

        for (int i = 0; i<imageItemMain.length; i++)
        {
            model_MyFavourite modelSubItem = new model_MyFavourite(textTitleSubItem[i],textPriceSubItem[i],imageItemMain[i],ratingSubItem[i]);
            myFavourites.add(modelSubItem);
        }

        RecyclerView recyclerView = findViewById(R.id.rcyMyFavourite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        RcyMyFavouriteAdapter adabter = new RcyMyFavouriteAdapter(myFavourites,this);
        recyclerView.setAdapter(adabter);
    }
}
