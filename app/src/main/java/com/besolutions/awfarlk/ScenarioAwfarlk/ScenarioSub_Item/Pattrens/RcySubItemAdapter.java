package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Pattrens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart_Popup;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens.Realm_adapter_Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Model.model_MyComparison;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Pattrens.Realm_adapter_MyComparison;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelSubItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class RcySubItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    TinyDB tinyDB;
    List<ModelSubItem> mMainGridList;
    Context mContext;

    Realm realm;
    Realm_adapter_MyComparison adapter;
    ArrayList<model_MyComparison> myComparisonslist = new ArrayList<>();




    public RcySubItemAdapter(List<ModelSubItem> songsList, Context context) {
        this.mMainGridList = songsList;
        this.mContext = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_item,parent,false);
        MainItemHolder mainHolder = new MainItemHolder(ads) ;
        return mainHolder;
    }

    public class MainHolder extends RecyclerView.ViewHolder{
        public MainHolder(View itemview) {
            super(itemview);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        tinyDB = new TinyDB(mContext);
        int viewType = getItemViewType(position);
        final ModelSubItem catrgory  = mMainGridList.get(position);


        MainItemHolder mainHolder =(MainItemHolder) holder;


        mainHolder.texttitle.setText(catrgory.getTxtTitle());
        mainHolder.textdescount.setText(catrgory.getTxtDiscount());
        mainHolder.txtprice.setText(catrgory.getTxtPrice());
        mainHolder.ratingBar.setRating(catrgory.getRatingStar());
        Glide.with(mContext)
                .load(catrgory.getImgSubItem())
                .placeholder(R.drawable.aircondition)
                .into(mainHolder.imagesubitem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB.putString("cartTitle",mMainGridList.get(position).getTxtTitle());
                tinyDB.putString("cartPrice",mMainGridList.get(position).getTxtPrice());
                tinyDB.putString("cartImage", String.valueOf(mMainGridList.get(position).getImgSubItem()));
                tinyDB.putString("cartRating", String.valueOf(mMainGridList.get(position).getRatingStar()));
//                tinyDB.putString("id_home",mMainGridList.get(position).getId());
                mContext.startActivity(new Intent(mContext, Product_Details.class));

            }
        });


        mainHolder.imgaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Cart_Popup cart_popup = new Cart_Popup();
//                cart_popup.dialog(context, R.layout.cart_popup, 1);
//                cart_popup.txtproductdetails.setText(txtproductdetails.getText().toString());
//                cart_popup.txtprice.setText(txtprice.getText().toString());


            }
        });


        mainHolder.imgfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mainHolder.imgcomparison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm = Realm.getDefaultInstance();
                adapter = new Realm_adapter_MyComparison(realm);
                myComparisonslist = adapter.retrieve();

                if (myComparisonslist.size() >= 2){

                    Toast.makeText(mContext, "Sorry You Cant Add More Than 2 Items", Toast.LENGTH_SHORT).show();

                }else {


                    AlertDialog.Builder alertDialogBulder = new AlertDialog.Builder(mContext);

                    alertDialogBulder.setTitle("أضف مقارنة");

                    alertDialogBulder
                            .setMessage("هل تريد مقارنة هذا المنتج ؟")
                            .setCancelable(false)
                            .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

//                                    String price = "";
//
//                                    Intent in = new Intent("delete_action");
//                                    in.putExtra("category", price);
//                                    context.sendBroadcast(in);


                                    realm = Realm.getDefaultInstance();
                                    adapter = new Realm_adapter_MyComparison(realm);
                                    adapter.retrieve();
                                    model_MyComparison model_myComparison = new model_MyComparison();

                                    model_myComparison.setImgSubItem(mMainGridList.get(position).getImgSubItem());
                                    model_myComparison.setRatingStar(mMainGridList.get(position).getRatingStar());
                                    model_myComparison.setTxtPrice(mMainGridList.get(position).getTxtPrice());
                                    model_myComparison.setTxtTitle(mMainGridList.get(position).getTxtTitle());


                                    Realm_adapter_MyComparison adapter_myComparison = new Realm_adapter_MyComparison(realm);
                                    adapter_myComparison.save(model_myComparison);
                                    Toast.makeText(mContext, "Your Item Add Successfully", Toast.LENGTH_SHORT).show();

                                }
                            }).setNegativeButton("لا", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();
                        }
                    });

                    //create it
                    AlertDialog alertDialog = alertDialogBulder.create();
                    // show it
                    alertDialog.show();

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mMainGridList.size();
    }


    public class MainItemHolder extends RecyclerView.ViewHolder
    {
        TextView texttitle,textdescount,txtprice;
        ImageView imagesubitem,imgaddtocart,imgfav,imgcomparison;
        RatingBar ratingBar;


        public MainItemHolder(@NonNull View itemView)
        {
            super(itemView);
            texttitle = itemView.findViewById(R.id.txtTitleSubItem);
            textdescount = itemView.findViewById(R.id.txtDiscountSub);
            txtprice = itemView.findViewById(R.id.txtPriceSubItem);
            imagesubitem = itemView.findViewById(R.id.imgSubItem);
            imgaddtocart = itemView.findViewById(R.id.imgAddToCartSubItem);
            imgfav = itemView.findViewById(R.id.imgFavSubItem);
            ratingBar = itemView.findViewById(R.id.ratings);
            imgcomparison = itemView.findViewById(R.id.imgComparisonSubItem);

        }

    }
    public interface OnItemListener {
        void onItemClick(int position);
    }


}
