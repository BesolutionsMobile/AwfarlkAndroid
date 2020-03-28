package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Pattrens;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.ModelCatrgory;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMain.Model.Model_Main_Rcy;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSubCategory.Controller.Sub_Category;
import com.besolutions.awfarlk.Utils.TinyDB;
import com.bumptech.glide.Glide;
import java.util.List;

public class RcyMainGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    TinyDB tinyDB;
    List<ModelCatrgory> mMainGridList;
    Context mContext;


    public RcyMainGridAdapter(List<ModelCatrgory> songsList, Context context) {
        this.mMainGridList = songsList;
        this.mContext = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        int x = mMainGridList.size() % 2;

        if (x == 0){

            if (viewType == 111){
                View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_raduis_top_left,parent,false);
                MainItemHolder holder = new MainItemHolder(ads);
                return holder;
            }else if (viewType == 222){
                View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_raduis_top_right, parent, false);
                MainItemHolder holder = new MainItemHolder(row);
                return holder;
            }else if (viewType == 333){
                View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_raduis_bottom_left, parent, false);
                MainItemHolder holder = new MainItemHolder(row);
                return holder;
            }else if (viewType == 444){
                View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_raduis_bottom_right, parent, false);
                MainItemHolder holder = new MainItemHolder(row);
                return holder;
            }else {

                View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
                MainItemHolder mainHolder = new MainItemHolder(ads);
                return mainHolder;
            }
        }else {

            if (viewType == 111){
                View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_raduis_top_left,parent,false);
                MainItemHolder holder = new MainItemHolder(ads);
                return holder;
            }else if (viewType == 222){
                View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_raduis_top_right, parent, false);
                MainItemHolder holder = new MainItemHolder(row);
                return holder;
            }else if (viewType == 444){
                View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_raduis_bottom_left, parent, false);
                MainItemHolder holder = new MainItemHolder(row);
                return holder;
            }else {

                View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
                MainItemHolder mainHolder = new MainItemHolder(ads);
                return mainHolder;
            }


        }


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
        final ModelCatrgory catrgory  = mMainGridList.get(position);


        MainItemHolder mainHolder =(MainItemHolder) holder;


        mainHolder.textmainitem.setText(catrgory.getName());

        Glide.with(mContext)
                .load(catrgory.getImage())
                .placeholder(R.drawable.itemimage)
                .into(mainHolder.imagemainitem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB = new TinyDB(mContext);
                tinyDB.putString("PageName",mMainGridList.get(position).getName());
                tinyDB.putString("CategoreId",mMainGridList.get(position).getId());
                mContext.startActivity(new Intent(mContext, Sub_Category.class));

            }
        });


    }

    @Override
    public int getItemCount() {
        return mMainGridList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){

            return 111;

        }else if (position==1){

            return 222;

        }else if (position == mMainGridList.size()-2){

            return 333;

        }else if (position == mMainGridList.size()-1){

            return  444;
        }else {

            return super.getItemViewType(position);
        }
    }

    public class MainItemHolder extends RecyclerView.ViewHolder
    {
        TextView textmainitem;
        ImageView imagemainitem;

        public MainItemHolder(@NonNull View itemView)
        {
            super(itemView);
            textmainitem = itemView.findViewById(R.id.txtMainItem);
            imagemainitem = itemView.findViewById(R.id.imgMainitem);

        }

    }
    public interface OnItemListener {
        void onItemClick(int position);
    }


}
