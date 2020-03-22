package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Model.model_MyFavourite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Pattrens.RcyMyFavouriteAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelSubItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Pattrens.RcySubItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFacvorite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_facvorite);


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
