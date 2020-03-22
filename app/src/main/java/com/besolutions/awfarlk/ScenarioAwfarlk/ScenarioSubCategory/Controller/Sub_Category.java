package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSubCategory.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.Model_Main_Rcy;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Pattrens.RcyMainGridAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSubCategory.Pattrens.RcySubCategoryAdapter;
import com.besolutions.awfarlk.Utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

public class Sub_Category extends AppCompatActivity {

    ImageView imgsearchtoolbar,imgbacktoolbar,imgcarttoolbar;
    RelativeLayout relativecarttoolbar;
    TextView txtpagenametoolbar,txtcartcountertoolbar;
    TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);


        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        imgcarttoolbar = findViewById(R.id.imgCart);
        relativecarttoolbar = findViewById(R.id.relativeCartToolbar);
        txtcartcountertoolbar = findViewById(R.id.txtCartCounter);
        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);
        tinyDB = new TinyDB(this);

        txtpagenametoolbar.setText(tinyDB.getString("PageName"));


        List<Model_Main_Rcy> main_rcyList = new ArrayList<>();

        int imageItemMain[] ={R.drawable.itemimage,R.drawable.itemimage, R.drawable.itemimage,
                R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,
                R.drawable.itemimage,R.drawable.itemimage, R.drawable.itemimage,
                R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage,R.drawable.itemimage};


        String textItemMain[] ={"اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية","اجهزة كهربائية",
                "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية","اجهزة كهربائية",
                "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية", "اجهزة كهربائية","اجهزة كهربائية"};

        for (int i = 0; i<imageItemMain.length; i++)
        {
            Model_Main_Rcy model_main_rcy = new Model_Main_Rcy(imageItemMain[i],textItemMain[i]);
            main_rcyList.add(model_main_rcy);
        }


        RecyclerView recyclerView = findViewById(R.id.rcyMain);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        RcySubCategoryAdapter adabter = new RcySubCategoryAdapter(main_rcyList,this);
        recyclerView.setAdapter(adabter);

        DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.HORIZONTAL);
        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        recyclerView.addItemDecoration(verticalDecoration);



    }
}
