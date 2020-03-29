package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioٍSearchResult.Pattrens;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.besolutions.awfarlk.NetworkLayer.Apicalls;
import com.besolutions.awfarlk.NetworkLayer.NetworkInterface;
import com.besolutions.awfarlk.NetworkLayer.ResponseModel;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.Dialog_Anim;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Model.model_MyComparison;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Pattrens.Realm_adapter_MyComparison;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelAddFavourite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelProduct;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioٍSearchResult.Model.ModelSearchProduct;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.besolutions.awfarlk.local_data.saved_data;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class RcySearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements NetworkInterface
{

    TinyDB tinyDB;
    List<ModelSearchProduct> mMainGridList;
    Context mContext;

    Realm realm;
    Realm_adapter_MyComparison adapter;
    ArrayList<model_MyComparison> myComparisonslist = new ArrayList<>();





    public RcySearchAdapter(List<ModelSearchProduct> songsList, Context context) {
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
        final ModelSearchProduct catrgory  = mMainGridList.get(position);


        final MainItemHolder mainHolder =(MainItemHolder) holder;


        mainHolder.texttitle.setText(catrgory.getTitle());
        mainHolder.textdescount.setText(catrgory.getDiscount());
        mainHolder.txtprice.setText(catrgory.getPriceAfterDiscount());
        mainHolder.ratingBar.setRating(Float.parseFloat(catrgory.getRating()));
        Glide.with(mContext)
                .load(catrgory.getImage())
                .placeholder(R.drawable.aircondition)
                .into(mainHolder.imagesubitem);

        if (catrgory.getFavorite()==1){

            mainHolder.imgfav.setImageResource(R.drawable.fav2);


        }else {

            mainHolder.imgfav.setImageResource(R.drawable.fav1);


        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB.putString("ProductId",mMainGridList.get(position).getId());

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

                String userId = saved_data.get_user_id(mContext);


                if (mMainGridList.get(position).getFavorite() == 0){

                    Sub_Item.loading.setVisibility(View.VISIBLE);
                    mainHolder.imgfav.setImageResource(R.drawable.fav1);
                    new Apicalls(mContext, RcySearchAdapter.this).add_favourite_product(userId,mMainGridList.get(position).getId());
                    notifyItemChanged(position);
                    mContext.startActivity(((AppCompatActivity) mContext).getIntent()); //REFRESH ACTIVITY
                    ((AppCompatActivity) mContext).overridePendingTransition(0, 0);//USING TO ANIMATE ZERO
                    Dialog_Anim dialog_anim = new Dialog_Anim();
                    dialog_anim.dialog(mContext,R.layout.like_dialog,.90);


                }else if (mMainGridList.get(position).getFavorite() == 1){

                    Sub_Item.loading.setVisibility(View.VISIBLE);
                    mainHolder.imgfav.setImageResource(R.drawable.fav2);
                    new Apicalls(mContext, RcySearchAdapter.this).delete_favourite_product(userId,mMainGridList.get(position).getId());
                    notifyItemChanged(position);
                    Dialog_Anim dialog_anim = new Dialog_Anim();
                    dialog_anim.dialog(mContext,R.layout.unlike_dialog,.90);
                    mContext.startActivity(((AppCompatActivity) mContext).getIntent()); //REFRESH ACTIVITY
                    ((AppCompatActivity) mContext).overridePendingTransition(0, 0);//USING TO ANIMATE ZERO


                }


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

                                    model_myComparison.setImgSubItem(Integer.parseInt(mMainGridList.get(position).getImage()));
                                    model_myComparison.setRatingStar(Float.parseFloat(mMainGridList.get(position).getRating()));
                                    model_myComparison.setTxtPrice(mMainGridList.get(position).getPriceAfterDiscount());
                                    model_myComparison.setTxtTitle(mMainGridList.get(position).getTitle());


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
    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {
        Sub_Item.loading.setVisibility(View.GONE);
        Gson gson = new Gson();

        ModelAddFavourite addFavourite = gson.fromJson(model.getResponse(),ModelAddFavourite.class);
        if (addFavourite.getStatus() == 1){

            Toasty.success(mContext, ""+addFavourite.getMessage(), Toast.LENGTH_LONG).show();


        }else if (addFavourite.getStatus() == 2){

            Toasty.error(mContext, ""+addFavourite.getMessage(), Toast.LENGTH_LONG).show();

        }else {

            Toasty.error(mContext, ""+addFavourite.getMessage(), Toast.LENGTH_LONG).show();


        }
    }

    @Override
    public void OnError(VolleyError error) {
        Sub_Item.loading.setVisibility(View.GONE);

    }

}
