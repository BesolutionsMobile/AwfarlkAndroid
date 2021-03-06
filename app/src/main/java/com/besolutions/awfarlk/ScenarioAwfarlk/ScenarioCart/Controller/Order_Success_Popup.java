package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;

import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Controller.MainActivity;

public class Order_Success_Popup {

    public void dialog(final Context context, int resource, double widthh) {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                context.startActivity(new Intent(context, MainActivity.class));

                dialog.dismiss();
            }
        }, 5000);


    }
}
