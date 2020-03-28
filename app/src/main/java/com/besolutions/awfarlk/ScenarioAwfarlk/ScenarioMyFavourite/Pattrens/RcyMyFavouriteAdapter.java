package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Pattrens;

import android.content.Context;
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
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Controller.MyFacvorite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Model.ModelProduct;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Model.model_MyFavourite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Controller.Sub_Item;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelAddFavourite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelSubItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Pattrens.RcySubItemAdapter;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.besolutions.awfarlk.local_data.saved_data;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class RcyMyFavouriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements NetworkInterface
{

    TinyDB tinyDB;
    List<ModelProduct> mMainGridList;
    Context mContext;


    public RcyMyFavouriteAdapter(List<ModelProduct> songsList, Context context) {
        this.mMainGridList = songsList;
        this.mContext = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_favourite_item,parent,false);
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
        final ModelProduct catrgory  = mMainGridList.get(position);


        final MainItemHolder mainHolder =(MainItemHolder) holder;


        mainHolder.texttitle.setText(catrgory.getTitle());
        mainHolder.txtprice.setText(catrgory.getPriceAfterDiscount());
        mainHolder.ratingBar.setRating(Float.parseFloat(catrgory.getRating()));
        Glide.with(mContext)
                .load(catrgory.getImage())
                .placeholder(R.drawable.aircondition)
                .into(mainHolder.imagefavourite);

        if (catrgory.getFavorite()==1){

            mainHolder.imgfav.setImageResource(R.drawable.fav2);


        }else {

            mainHolder.imgfav.setImageResource(R.drawable.fav1);
        }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                tinyDB.putString("id_home",mMainGridList.get(position).getId());
//                mContext.startActivity(new Intent(mContext, Product_Details.class));

            }
        });


        mainHolder.imgaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        mainHolder.imgfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userId = saved_data.get_user_id(mContext);


                if (mMainGridList.get(position).getFavorite() == 0){

                    MyFacvorite.loading.setVisibility(View.VISIBLE);
                    mainHolder.imgfav.setImageResource(R.drawable.fav1);
                    new Apicalls(mContext, RcyMyFavouriteAdapter.this).add_favourite_product(userId,mMainGridList.get(position).getId());
                    notifyItemChanged(position);
                    mContext.startActivity(((AppCompatActivity) mContext).getIntent()); //REFRESH ACTIVITY
                    ((AppCompatActivity) mContext).overridePendingTransition(0, 0);//USING TO ANIMATE ZERO
                    Dialog_Anim dialog_anim = new Dialog_Anim();
                    dialog_anim.dialog(mContext,R.layout.like_dialog,.90);


                }else if (mMainGridList.get(position).getFavorite() == 1){

                    MyFacvorite.loading.setVisibility(View.VISIBLE);
                    mainHolder.imgfav.setImageResource(R.drawable.fav2);
                    new Apicalls(mContext,RcyMyFavouriteAdapter.this).delete_favourite_product(userId,mMainGridList.get(position).getId());
                    notifyItemChanged(position);
                    Dialog_Anim dialog_anim = new Dialog_Anim();
                    dialog_anim.dialog(mContext,R.layout.unlike_dialog,.90);
                    mContext.startActivity(((AppCompatActivity) mContext).getIntent()); //REFRESH ACTIVITY
                    ((AppCompatActivity) mContext).overridePendingTransition(0, 0);//USING TO ANIMATE ZERO


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
        TextView texttitle,txtprice;
        ImageView imagefavourite,imgaddtocart,imgfav;
        RatingBar ratingBar;


        public MainItemHolder(@NonNull View itemView)
        {
            super(itemView);
            texttitle = itemView.findViewById(R.id.txtTitleMyFavourite);
            txtprice = itemView.findViewById(R.id.txtPriceMyFavourite);
            imagefavourite = itemView.findViewById(R.id.imgMyFavourite);
            imgaddtocart = itemView.findViewById(R.id.imgAddToCartMyFavourite);
            imgfav = itemView.findViewById(R.id.imgFavMyFavourite);
            ratingBar = itemView.findViewById(R.id.ratings);

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
        MyFacvorite.loading.setVisibility(View.GONE);

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
        MyFacvorite.loading.setVisibility(View.GONE);

    }



}
