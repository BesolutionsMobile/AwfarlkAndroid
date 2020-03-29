package com.besolutions.awfarlk.ScenarioAwfarlk;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioHome.Controller.Login;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioٍSearchResult.Controller.SearchResult;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class Search_Popup {
    Context context;
    public EditText editsearch;
    Button btnsearch;
    TinyDB tinyDB;
    int num = 1;

    public void dialog(final Context context, int resource, double widthh) {
        this.context = context;
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        tinyDB = new TinyDB(context);
        btnsearch = dialog.findViewById(R.id.btnSearch);
        editsearch = dialog.findViewById(R.id.editSearch);

        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);


        WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();

        wlp.gravity = Gravity.TOP;
        dialog.getWindow().setAttributes(wlp);


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editsearch.getText().toString().equals("") || editsearch.getText() == null) {

                    editsearch.setError("Please Enter Your Search Word ");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(dialog.findViewById(R.id.editSearch));


                } else {

                    tinyDB.putString("searchtext", editsearch.getText().toString());
                    context.startActivity(new Intent(context, SearchResult.class));

                }
            }
        });


        dialog.show();


    }
}
