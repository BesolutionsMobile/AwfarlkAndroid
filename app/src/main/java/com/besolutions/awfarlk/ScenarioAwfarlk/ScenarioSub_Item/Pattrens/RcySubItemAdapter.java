package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Pattrens;

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
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model.ModelSubItem;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Controller.Product_Details;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.bumptech.glide.Glide;
import java.util.List;

public class RcySubItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    TinyDB tinyDB;
    List<ModelSubItem> mMainGridList;
    Context mContext;


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

//                tinyDB.putString("id_home",mMainGridList.get(position).getId());
                mContext.startActivity(new Intent(mContext, Product_Details.class));

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
        TextView texttitle,textdescount,txtprice;
        ImageView imagesubitem,imgaddtocart,imgfav;
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

        }

    }
    public interface OnItemListener {
        void onItemClick(int position);
    }


}
