package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.besolutions.awfarlk.NetworkLayer.Apicalls;
import com.besolutions.awfarlk.NetworkLayer.NetworkInterface;
import com.besolutions.awfarlk.NetworkLayer.ResponseModel;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioAboutUs.Controller.About_Us;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart_Popup;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioContactUs.Controller.Contact_Us;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Controller.FAQ;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.ModelAllGategorey;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.ModelCatrgory;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.Model_Main_Rcy;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Pattrens.RcyMainGridAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Controller.My_Comparison;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Controller.MyFacvorite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioPersonalInfo.Controller.PersonalInfo;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.ScenarioAwfarlk.Search_Popup;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements NetworkInterface {


    DrawerLayout drawerLayout;
    ImageView imgsearchtoolbar,imgdrawertoolbar;
    RelativeLayout relativecarttoolbar;
    TextView txtpagenametoolbar;
    Realm realm;
    Realm_adapter_Cart realm_adapter_cart;
    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();
    ImageView imagecart;
    public static TextView txtcartcounter;
    LinearLayout loading;
    List<ModelCatrgory> catrgoryList = new ArrayList<>();
    ModelCatrgory[] catrgories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


                startActivity(new Intent(MainActivity.this, Cart.class));

            }
        });

        drawerLayout =findViewById(R.id.drawer);
        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgdrawertoolbar = findViewById(R.id.imgDrawerToolbar);
        relativecarttoolbar = findViewById(R.id.relativeCartToolbar);
        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        loading = findViewById(R.id.loading);
        txtpagenametoolbar.setText("Category");

        imgsearchtoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Search_Popup search_popup = new Search_Popup();
                search_popup.dialog(MainActivity.this, R.layout.search_popup, 1);


            }
        });



        loading.setVisibility(View.VISIBLE);
        new Apicalls(MainActivity.this,MainActivity.this).get_all_categorey();



        menu();
        onClick_navigation();
//        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                DividerItemDecoration.VERTICAL);
//        Drawable horizontalDivider = ContextCompat.getDrawable(this, R.drawable.horizontal_divider);
//        horizontalDecoration.setDrawable(horizontalDivider);
//        recyclerView.addItemDecoration(horizontalDecoration);

    }

    public void menu() {
        ImageView menu = findViewById(R.id.imgDrawerToolbar);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
    }

    void onClick_navigation() {

        //GO TO Cart
        LinearLayout linearCategory = findViewById(R.id.linearCategory);
        linearCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

                startActivity(new Intent(MainActivity.this, MainActivity.class));
//                finish();

            }
        });

        //GO TO Cart
        LinearLayout linearMyFavourite = findViewById(R.id.linearMyFavourite);
        linearMyFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

                startActivity(new Intent(MainActivity.this, MyFacvorite.class));
//                finish();

            }
        });

        //Go To Information Details

        LinearLayout linearCart = findViewById(R.id.linearCart);
        linearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

                startActivity(new Intent(MainActivity.this, Cart.class));
//                finish();

            }
        });

        //Go To MyComparison
        LinearLayout linearMyComparison = findViewById(R.id.linearComparison);
        linearMyComparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

                startActivity(new Intent(MainActivity.this, My_Comparison.class));
//                finish();

            }
        });

        //Go To faq

        LinearLayout linearfaq = findViewById(R.id.linearFAQ);
        linearfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

                startActivity(new Intent(MainActivity.this, FAQ.class));
//                finish();

            }
        });

        //Go To ContactUs

        LinearLayout linearContactUs = findViewById(R.id.linearContactUs);
        linearContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

                startActivity(new Intent(MainActivity.this, Contact_Us.class));
//                finish();

            }
        });

        //Go To AboutUs

        LinearLayout linearaboutus = findViewById(R.id.linearAboutUs);
        linearaboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

                startActivity(new Intent(MainActivity.this, About_Us.class));
//                finish();

            }
        });

        //GO TO LogOut
        LinearLayout linearLogOut = findViewById(R.id.linearLogOut);
        linearLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

//                startActivity(new Intent(MainActivity.this, MainActivity.class));
//                finish();

            }
        });

        //GO TO LogOut
        LinearLayout linearPersonal = findViewById(R.id.linearProfile);
        linearPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);

                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }

                startActivity(new Intent(MainActivity.this, PersonalInfo.class));
//                finish();

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

        ModelAllGategorey allGategorey = gson.fromJson(model.getResponse(),ModelAllGategorey.class);

        catrgories = allGategorey.getCatrgories();
        for (int i = 0; i<catrgories.length; i++){

            ModelCatrgory catrgory = new ModelCatrgory();

            catrgory.setAdv(catrgories[i].getAdv());
            catrgory.setId(catrgories[i].getId());
            catrgory.setImage(catrgories[i].getImage());
            catrgory.setName(catrgories[i].getName());

            catrgoryList.add(catrgory);
        }

        RecyclerView recyclerView = findViewById(R.id.rcyMain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        RcyMainGridAdapter adabter = new RcyMainGridAdapter(catrgoryList,this);
        recyclerView.setAdapter(adabter);

        DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.HORIZONTAL);
        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        recyclerView.addItemDecoration(verticalDecoration);

    }

    @Override
    public void OnError(VolleyError error) {
        loading.setVisibility(View.GONE);

    }
}
