package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.besolutions.awfarlk.R;

public class Cart_Popup {
    Context context;


    public void dialog(final Context context, int resource, double widthh) {
        this.context = context;
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);


        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);


        WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(wlp);







        dialog.show();


    }
}
