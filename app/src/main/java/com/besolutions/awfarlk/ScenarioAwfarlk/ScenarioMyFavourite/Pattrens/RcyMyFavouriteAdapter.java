package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Pattrens;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Model.model_MyFavourite;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelSubItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.bumptech.glide.Glide;

import java.util.List;

public class RcyMyFavouriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    TinyDB tinyDB;
    List<model_MyFavourite> mMainGridList;
    Context mContext;


    public RcyMyFavouriteAdapter(List<model_MyFavourite> songsList, Context context) {
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
        final model_MyFavourite catrgory  = mMainGridList.get(position);


        MainItemHolder mainHolder =(MainItemHolder) holder;


        mainHolder.texttitle.setText(catrgory.getTxtTitle());
        mainHolder.txtprice.setText(catrgory.getTxtPrice());
        mainHolder.ratingBar.setRating(catrgory.getRatingStar());
        Glide.with(mContext)
                .load(catrgory.getImgSubItem())
                .placeholder(R.drawable.aircondition)
                .into(mainHolder.imagefavourite);
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


}
