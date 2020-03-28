package com.besolutions.awfarlk.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class send_data {
    // SET BRANCH ID
    public static void id_Order(Context context, String branch_id)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("branch_id",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("branch_id", branch_id);
        editor.commit();
    }

    //SET PRODUCT ID
    public static void SET_PRODUCT_ID(Context context, String product_id)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("product_id",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("product_id", product_id);
        editor.commit();
    }

    //SET USER NAME
    public static void SET_USER_NAME(Context context, String user_name)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_name",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("user_name", user_name);
        editor.commit();
    }

    //SET EMAIL
    public static void SET_USER_EMAIL(Context context, String user_email)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_email",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("user_email", user_email);
        editor.commit();
    }

    //SET PHONE
    public static void SET_USER_PHONE(Context context, String user_phone)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_phone",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("user_phone", user_phone);
        editor.commit();
    }

    //SET USER ID
    public static void SET_USER_ID(Context context, String user_id)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_id",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("user_id", user_id);
        editor.commit();
    }

    public static void userId_check(Context context , boolean value)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("id",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("islogin",value);
        editor.commit();
    }

}
