package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Model.model_MyComparison;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Pattrens.RcyMyComparisonAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Model.model_MyFavourite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Pattrens.RcyMyFavouriteAdapter;

import java.util.ArrayList;
import java.util.List;

public class My_Comparison extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comparison);


        List<model_MyComparison> myFavourites = new ArrayList<>();

        int imageItemMain[] ={R.drawable.drpicture,R.drawable.drpicture};


        String textTitleSubItem[] ={"تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)", "تكييف يونيون اير تكييف 5 حصان - بار / سخن (Artify)"};


        String textPriceSubItem[] ={"1220", "1880"};

        float ratingSubItem[] ={1.255f, 3.555f};

        for (int i = 0; i<imageItemMain.length; i++)
        {
            model_MyComparison modelSubItem = new model_MyComparison(textTitleSubItem[i],textPriceSubItem[i],imageItemMain[i],ratingSubItem[i]);
            myFavourites.add(modelSubItem);
        }

        RecyclerView recyclerView = findViewById(R.id.rcyMyComparison);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        RcyMyComparisonAdapter adabter = new RcyMyComparisonAdapter(myFavourites,this);
        recyclerView.setAdapter(adabter);
    }
}
