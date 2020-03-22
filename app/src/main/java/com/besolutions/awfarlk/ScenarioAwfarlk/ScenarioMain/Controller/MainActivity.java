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

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioAboutUs.Controller.About_Us;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioContactUs.Controller.Contact_Us;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioFAQ.Controller.FAQ;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.Model_Main_Rcy;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Pattrens.RcyMainGridAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Controller.My_Comparison;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Controller.MyFacvorite;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    ImageView imgsearchtoolbar,imgdrawertoolbar,imgcarttoolbar;
    RelativeLayout relativecarttoolbar;
    TextView txtpagenametoolbar,txtcartcountertoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout =findViewById(R.id.drawer);
        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgdrawertoolbar = findViewById(R.id.imgDrawerToolbar);
        imgcarttoolbar = findViewById(R.id.imgCart);
        relativecarttoolbar = findViewById(R.id.relativeCartToolbar);
        txtcartcountertoolbar = findViewById(R.id.txtCartCounter);
        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);


        txtpagenametoolbar.setText("Category");
        List<Model_Main_Rcy> main_rcyList = new ArrayList<>();

        int imageItemMain[] ={R.drawable.itemimage,R.drawable.itemimage, R.drawable.itemimage,
                R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,
                R.drawable.itemimage,R.drawable.itemimage, R.drawable.itemimage,
                R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage};


        String textItemMain[] ={"أحذية ملابس اكسيسوارات", "أحذية ملابس اكسيسوارات", "أحذية ملابس اكسيسوارات", "أحذية ملابس اكسيسوارات", "أحذية ملابس اكسيسوارات","أحذية ملابس اكسيسوارات",
                "أحذية ملابس اكسيسوارات", "أحذية ملابس اكسيسوارات", "أحذية ملابس اكسيسوارات", "أحذية ملابس اكسيسوارات", "أحذية ملابس اكسيسوارات","أحذية ملابس اكسيسوارات",
                "أحذية ملابس اكسيسوارات", "أحذية ملابس اكسيسوارات", "أحذية ملابس اكسيسوارات", "أحذية ملابس اكسيسوارات","أحذية ملابس اكسيسوارات"};

        for (int i = 0; i<imageItemMain.length; i++)
        {
            Model_Main_Rcy model_main_rcy = new Model_Main_Rcy(imageItemMain[i],textItemMain[i]);
            main_rcyList.add(model_main_rcy);
        }


        RecyclerView recyclerView = findViewById(R.id.rcyMain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        RcyMainGridAdapter adabter = new RcyMainGridAdapter(main_rcyList,this);
        recyclerView.setAdapter(adabter);

        DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.HORIZONTAL);
        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        recyclerView.addItemDecoration(verticalDecoration);

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


    }
}
