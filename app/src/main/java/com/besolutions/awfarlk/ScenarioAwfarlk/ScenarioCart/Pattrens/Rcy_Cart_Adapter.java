package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Pattrens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.besolutions.awfarlk.R;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Controller.Cart;
import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model.Realm_Cart_Model;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import io.realm.Realm;

public class Rcy_Cart_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Realm_Cart_Model> rcyHomelList;

    Realm realm;
    Realm_adapter_Cart adapter;
    Context context;
    int num = 1;

    ArrayList<Realm_Cart_Model> cartModels = new ArrayList<>();

    public Rcy_Cart_Adapter(List<Realm_Cart_Model> rcyHomelList, Context context ) {

        this.context = context;
        this.rcyHomelList = rcyHomelList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        ItemeCartHolder holder = new ItemeCartHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final Realm_Cart_Model songs = rcyHomelList.get(position);


        final ItemeCartHolder itemeCartHolder = (ItemeCartHolder) holder;
        itemeCartHolder.txttitle.setText(songs.getTxttitle());

        itemeCartHolder.txtprice.setText(songs.getTxtprice());
        itemeCartHolder.txtnumber.setText(songs.getTxtnumberchoose());
        itemeCartHolder.txtpricediscount.setText(songs.getTxtpricediscount());
        itemeCartHolder.ratingBar.setRating(songs.getRatingStar());


        Glide.with(context)
                .load(songs.getImghome())
                .placeholder(R.drawable.aircondition)
                .into(itemeCartHolder.imgitemhome);

        itemeCartHolder.txtincrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                int price = Integer.parseInt(songs.getTxtprice());
//                int selectrd = num * price;
//
//                itemeCartHolder.txtprice.setText(""+selectrd);
//                totalPrice1 = Integer.parseInt(price_details[position].getPrice());

                num = Integer.parseInt(itemeCartHolder.txtnumber.getText().toString());
                num++;
                if (num < 30) {
                    itemeCartHolder.txtnumber.setText("" + num);
//                    itemeCartHolder.txtprice.setText("" + num * totalPrice1);
                    realm = Realm.getDefaultInstance();
                    adapter = new Realm_adapter_Cart(realm);
                    adapter.updaatenumberofboxes(songs.getId(),itemeCartHolder.txtnumber.getText().toString());

                    totalPrices();


                } else if (num > 30) {
                    num = 30;
                    itemeCartHolder.txtnumber.setText("" + num);
//                    itemeCartHolder.txtprice.setText("" + num * totalPrice1);
                    realm = Realm.getDefaultInstance();
                    adapter = new Realm_adapter_Cart(realm);
                    adapter.updaatenumberofboxes(songs.getId(),itemeCartHolder.txtnumber.getText().toString());
                    totalPrices();
                }

            }
        });

        itemeCartHolder.txtdecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num = Integer.parseInt(itemeCartHolder.txtnumber.getText().toString());
                num--;
                if (num >= 1) {
                    itemeCartHolder.txtnumber.setText("" + num);
//                    txtprice.setText("" + num * totalPrice1);
                    realm = Realm.getDefaultInstance();
                    adapter = new Realm_adapter_Cart(realm);
                    adapter.updaatenumberofboxes(songs.getId(),itemeCartHolder.txtnumber.getText().toString());
                    totalPrices();

                } else if (num <= 0) {
                    num = 1;
                    itemeCartHolder.txtnumber.setText("" + num);
//                    txtprice.setText("" + num * totalPrice1);
                    realm = Realm.getDefaultInstance();
                    adapter = new Realm_adapter_Cart(realm);
                    adapter.updaatenumberofboxes(songs.getId(),itemeCartHolder.txtnumber.getText().toString());
                    totalPrices();
                }

            }
        });

        itemeCartHolder.imgdeleteitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBulder = new AlertDialog.Builder(context);

                alertDialogBulder.setTitle("حذف المنتج");

                alertDialogBulder
                        .setMessage("هل انت متاكد من حذف هذا المنتج !!")
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
                                adapter = new Realm_adapter_Cart(realm);
                                adapter.delete(position);
                                adapter.retrieve();
                                rcyHomelList.remove(position);
                                notifyItemRemoved(position);

                                Cart cart = new Cart();
                                cart.setcartcount();
                                totalPrices();
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
        });

        itemeCartHolder.imgfavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


    }


    @Override
    public int getItemCount() {
        return rcyHomelList.size();
    }


    public static class ItemeCartHolder extends RecyclerView.ViewHolder {
        private final TextView txttitle, txtpricediscount, txtprice, txtincrease,txtdecrease, txtnumber;
        private final ImageView imgitemhome,imgdeleteitem,imgfavourite;
        private final RatingBar ratingBar;



        public ItemeCartHolder(@NonNull View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.txtItemTitle);
            txtpricediscount = itemView.findViewById(R.id.txtPriceSubItemDiscount);

            txtprice = itemView.findViewById(R.id.txtPriceSubItem);
            imgitemhome = itemView.findViewById(R.id.imgCartImage);
            imgdeleteitem = itemView.findViewById(R.id.imgDeleteItem);
            imgfavourite = itemView.findViewById(R.id.imgFavourite);
            ratingBar = itemView.findViewById(R.id.ratings);

            txtincrease = itemView.findViewById(R.id.txtProductDetailsIncrease);
            txtdecrease = itemView.findViewById(R.id.txtProductDetailsDecrease);
            txtnumber = itemView.findViewById(R.id.txtNumber);


        }
    }
    public void totalPrices(){

        realm = Realm.getDefaultInstance();
        adapter = new Realm_adapter_Cart(realm);
        cartModels = adapter.retrieve();
        int toatalprice = 0;
        for (int x=0; x<adapter.retrieve().size();x++){

            int price = Integer.parseInt(cartModels.get(x).getTxtprice());
            int number = Integer.parseInt(cartModels.get(x).getTxtnumberchoose());

            int totalpricebefortax = price * number;

            toatalprice += totalpricebefortax ;

        }
//        int taxPercentage = Integer.parseInt(saved_data.get_Tax_Percentage(context));
//        double taxpercent = (double) taxPercentage/100;
//        double tax = toatalprice * taxpercent;
//
//
//        int discountPercentage = Integer.parseInt(saved_data.get_Discount_Percentage(context));
//        double discountpercent = (double) discountPercentage/100;
//        double discount = toatalprice * discountpercent;
//
//
//        int delivary = Integer.parseInt(saved_data.get_Delevary(context));
//
//        double totalpriceaftertaxes = toatalprice + tax - discount + delivary;


        Cart cart = new Cart();
        cart.setToatal(String.valueOf(toatalprice));

    }
}