package com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Pattrens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model.ProductComment;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenariosProductDetails.Model.RcyCommentModel;
import com.bumptech.glide.Glide;
import java.util.List;

public class Rcy_Product_details_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<ProductComment> rcyHomelList;

    Context context;
    public static int x = 0;


    public Rcy_Product_details_Adapter(List<ProductComment> rcyHomelList, Context context) {

        this.context = context;
        this.rcyHomelList = rcyHomelList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_details_comments, parent, false);
        ItemeCartHolder holder = new ItemeCartHolder(ads);


        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final ProductComment songs = rcyHomelList.get(position);


        ItemeCartHolder itemeCartHolder = (ItemeCartHolder) holder;
        itemeCartHolder.txtcommentname.setText(songs.getUserName());
        itemeCartHolder.txtComments.setText(songs.getComment());
        Glide.with(context)
                .load(songs.getUserPhoto())
                .placeholder(R.drawable.drpicture)
                .into(itemeCartHolder.imgcommentphoto);




    }


    @Override
    public int getItemCount() {


        return rcyHomelList.size();
    }


    public static class ItemeCartHolder extends RecyclerView.ViewHolder {
        private final TextView txtcommentname,txtComments;
        public static ImageView imgcommentphoto;



        public ItemeCartHolder(@NonNull View itemView) {
            super(itemView);
            txtcommentname = itemView.findViewById(R.id.txtCommentName);
            txtComments = itemView.findViewById(R.id.txtComment);
            imgcommentphoto = itemView.findViewById(R.id.profilePhoto);





        }
    }
}