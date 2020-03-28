package com.besolutions.awfarlk.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class saved_data {
    public static String get_Order_id(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("branch_id",MODE_PRIVATE);
        String branch_id=sharedPreferences.getString("branch_id","0");
        return branch_id;
    }



    public String get_product_id(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("product_id",MODE_PRIVATE);
        String product_id=sharedPreferences.getString("product_id","0");
        return product_id;
    }

    public static String get_user_name(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_name",MODE_PRIVATE);
        String user_name=sharedPreferences.getString("user_name","0");
        return user_name;
    }

    public static String get_user_email(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_email",MODE_PRIVATE);
        String user_email=sharedPreferences.getString("user_email","0");
        return user_email;
    }

    public static String get_user_phone(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_phone",MODE_PRIVATE);
        String user_phone=sharedPreferences.getString("user_phone","0");
        return user_phone;
    }

    public static String get_user_id(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_id",MODE_PRIVATE);
        String user_id=sharedPreferences.getString("user_id","0");
        return user_id;
    }

    public static boolean get_user_check(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("id",MODE_PRIVATE);
        boolean value=sharedPreferences.getBoolean("islogin",false);
        return value;
    }

}
