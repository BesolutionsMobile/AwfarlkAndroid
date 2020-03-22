package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.Model_Main_Rcy;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Pattrens.RcyMainGridAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelSubItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Pattrens.RcySubItemAdapter;
import com.besolutions.awfarlk.Utils.TinyDB;

import java.util.ArrayList;
import java.util.List;

public class Sub_Item extends AppCompatActivity {

    ImageView imgsearchtoolbar,imgbacktoolbar,imgcarttoolbar;
    TextView txtpagenametoolbar,txtcartcountertoolbar;

    TinyDB tinyDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_item);

        imgsearchtoolbar = findViewById(R.id.imgSearchToolbar);
        imgbacktoolbar = findViewById(R.id.imgBackToolbar);
        imgcarttoolbar = findViewById(R.id.imgCart);
        txtcartcountertoolbar = findViewById(R.id.txtCartCounter);
        txtpagenametoolbar = findViewById(R.id.txtPageNameToolbar);

        tinyDB = new TinyDB(this);

        txtpagenametoolbar.setText(tinyDB.getString("PageSubName"));


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

        RecyclerView recyclerView = findViewById(R.id.rcySubItem);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        RcySubItemAdapter adabter = new RcySubItemAdapter(subItemList,this);
        recyclerView.setAdapter(adabter);

        DividerItemDecoration verticalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.vertical_divider);
        verticalDecoration.setDrawable(verticalDivider);
        recyclerView.addItemDecoration(verticalDecoration);

    }
}
