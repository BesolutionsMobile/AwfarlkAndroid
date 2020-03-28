package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Controller.My_Comparison;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.Utils.TinyDB;

import io.realm.Realm;

public class Cart_Popup {
    Context context;
    public TextView txtincrease, txtdecrease,txtnumber,txtprice,txtproductdetails;
    LinearLayout linearaddtocart;
    TinyDB tinyDB;
    Realm realm;
    int num = 1;

    public void dialog(final Context context, int resource, double widthh) {
        this.context = context;
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);

        tinyDB = new TinyDB(context);
        realm = Realm.getDefaultInstance();
        txtincrease = dialog.findViewById(R.id.txtProductDetailsIncrease);
        txtdecrease = dialog.findViewById(R.id.txtProductDetailsDecrease);
        txtnumber = dialog.findViewById(R.id.txtNumber);
        txtprice = dialog.findViewById(R.id.txtPriceSubItem);
        linearaddtocart = dialog.findViewById(R.id.linearAddToCart);
        txtproductdetails = dialog.findViewById(R.id.txtProductDetails);


        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);


        WindowManager.LayoutParams wlp = dialog.getWindow().getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(wlp);


        linearaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm_Cart_Model c = new Realm_Cart_Model();
                tinyDB = new TinyDB(context);
                String title =  tinyDB.getString("cartTitle");
                String price = tinyDB.getString("cartPrice");
                int image = Integer.parseInt(tinyDB.getString("cartImage"));
                float rating = Float.parseFloat(tinyDB.getString("cartRating"));
                String product_id = tinyDB.getString("idproductdetails");

                c.setTxttitle(title);
                c.setImghome(image);
                c.setRatingStar(rating);
                c.setTxtprice(price);
                c.setTxtnumberchoose(txtnumber.getText().toString());
                c.setTxtpricediscount(price);
                c.setProduct_id(product_id);

                Realm_adapter_Cart adapterCart = new Realm_adapter_Cart(realm);
                adapterCart.save(c);

                dialog.dismiss();

                Product_Details product_details = new Product_Details();
                product_details.setcartcount();

//                My_Comparison my_comparison = new My_Comparison();
//                my_comparison.setcartcount();
                context.startActivity(((AppCompatActivity) context).getIntent()); //REFRESH ACTIVITY
                ((AppCompatActivity) context).overridePendingTransition(0, 0);//USING TO ANIMATE ZERO

            }
        });



        txtincrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int price = Integer.parseInt(songs.getTxtprice());
//                int selectrd = num * price;
//
//                itemeCartHolder.txtprice.setText(""+selectrd);
//                totalPrice1 = Integer.parseInt(price_details[position].getPrice());

                num = Integer.parseInt(txtnumber.getText().toString());
                num++;
                if (num < 30) {
                    txtnumber.setText("" + num);
//                    itemeCartHolder.txtprice.setText("" + num * totalPrice1);

                } else if (num > 30) {
                    num = 30;
                    txtnumber.setText("" + num);
//                    itemeCartHolder.txtprice.setText("" + num * totalPrice1);

                }

            }
        });

        txtdecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num = Integer.parseInt(txtnumber.getText().toString());
                num--;
                if (num >= 1) {
                    txtnumber.setText("" + num);
//                    txtprice.setText("" + num * totalPrice1);

                } else if (num <= 0) {
                    num = 1;
                    txtnumber.setText("" + num);
//                    txtprice.setText("" + num * totalPrice1);
                }

            }
        });


        dialog.show();


    }
}
