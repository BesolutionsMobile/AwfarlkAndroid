package com.besolutions.awfarlk.NetworkLayer;

import android.content.Context;

import com.android.volley.Request;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @desc Java Api Calls Contains all the Project Calls
 */

public class Apicalls
{

    private APIRouter apiRouter;

    public Apicalls(Context context, NetworkInterface networkInterface)
    {

        apiRouter = new APIRouter(context, networkInterface);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func User Login
     */

    public void loginUser(final String email, final String password) {

        apiRouter.performRequest(Apiclient.LOGIN_USER.getURL(),Apiclient.LOGIN_USER.getParams(), Arrays.asList(email,password), Request.Method.POST,Apiclient.LOGIN_USER.getCode());

    }


    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func User Registration
     */

    public void registerUser(final String Name, final String Email, final String Phone, final String Password)
    {

        apiRouter.performRequest(Apiclient.Register_Uer.getURL(),Apiclient.Register_Uer.getParams(), Arrays.asList(Name,Email,Phone,Password), Request.Method.POST,Apiclient.Register_Uer.getCode());


    }

    //----------------------------------------------------------------------------------------------



    /**
     *
     * @func Edit User Profile
     */

    public  void get_all_categorey ()
    {

        apiRouter.performRequest(Apiclient.GET_ALL_CATEGOREY.getURL(),Apiclient.GET_ALL_CATEGOREY.getParams(),null, Request.Method.GET,Apiclient.GET_ALL_CATEGOREY.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Add a new Ad
     */

    public  void get_all_sub_category (final String idCategory)
    {

        apiRouter.performRequest(Apiclient.GET_ALL_SUB_CATEGORY.getURL()+"/"+idCategory,Apiclient.GET_ALL_SUB_CATEGORY.getParams(),null, Request.Method.GET,Apiclient.GET_ALL_SUB_CATEGORY.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */

    public void get_all_product_off_sub_category (final String ID_Sub_Category ,final String ID_User,final String Page_Number)
    {

        apiRouter.performRequest(Apiclient.GET_All_PRODUCT_OFF_SUB_CATEGORY.getURL()+"/"+ID_Sub_Category+"/"+ID_User+"/"+Page_Number,Apiclient.GET_All_PRODUCT_OFF_SUB_CATEGORY.getParams(),null, Request.Method.GET, Apiclient.GET_All_PRODUCT_OFF_SUB_CATEGORY.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */


    public void add_favourite_product (final String ID_USER,final String ID_Product )
    {

        apiRouter.performRequest(Apiclient.ADD_FAVOURITE_PRODUCT.getURL()+"/"+ID_USER+"/"+ID_Product,Apiclient.ADD_FAVOURITE_PRODUCT.getParams(),null, Request.Method.GET, Apiclient.ADD_FAVOURITE_PRODUCT.getCode());

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Main Activity Ads
     *
     */

    public void delete_favourite_product (final String ID_USER,final String ID_Product)
    {

        apiRouter.performRequest(Apiclient.DELETE_FAVOURITE_PRODUCT.getURL()+"/"+ID_USER+"/"+ID_Product,Apiclient.DELETE_FAVOURITE_PRODUCT.getParams(),null, Request.Method.GET,Apiclient.DELETE_FAVOURITE_PRODUCT.getCode());

    }




    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Main Activity Ads
     *
     */

    public void get_all_favourite_product (final String ID_USER)
    {

        apiRouter.performRequest(Apiclient.GET_ALL_FAVOURITE_PRODUCT.getURL()+"/"+ID_USER,Apiclient.GET_ALL_FAVOURITE_PRODUCT.getParams(),null, Request.Method.GET,Apiclient.GET_ALL_FAVOURITE_PRODUCT.getCode());

    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Main Activity Ads
     *
     */

    public void edit_profile (final String Name,final String Mail,final String Phone,final String Id_user)
    {

        apiRouter.performRequest(Apiclient.EDIT_PROFILE.getURL(),Apiclient.EDIT_PROFILE.getParams(),Arrays.asList(Name,Mail,Phone,Id_user), Request.Method.POST,Apiclient.EDIT_PROFILE.getCode());

    }





    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */

    public void view_profile (final String Id_user)
    {

        apiRouter.performRequest(Apiclient.VIEW_PROFILE.getURL()+"/"+Id_user,Apiclient.VIEW_PROFILE.getParams(),null, Request.Method.GET,Apiclient.VIEW_PROFILE.getCode());

    }

    //----------------------------------------------------------------------------------------------



    /**
     *
     * @func Main Activity Ads
     *
     */

    public void change_password (final String Old_password,final String New_password,final String Re_new_password,final String Id_user)
    {

        apiRouter.performRequest(Apiclient.CHANGE_PASSWORD.getURL(),Apiclient.CHANGE_PASSWORD.getParams(), Arrays.asList(Old_password,New_password, Re_new_password,Id_user), Request.Method.POST,Apiclient.CHANGE_PASSWORD.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */

    public void sendOrder (final String products,final String name,final String address,final String mobile,final String id_region,final String payment,final String id_user)
    {

        apiRouter.performRequest(Apiclient.SEND_ORDER.getURL(),Apiclient.SEND_ORDER.getParams(), Arrays.asList(products,name,address,mobile,id_region,payment,id_user), Request.Method.POST,Apiclient.SEND_ORDER.getCode());

    }

    //----------------------------------------------------------------------------------------------




    public void faq_question ()
    {

        apiRouter.performRequest(Apiclient.FAQ_QUESTION.getURL(),Apiclient.FAQ_QUESTION.getParams(), null, Request.Method.POST,Apiclient.FAQ_QUESTION.getCode());

    }

    //----------------------------------------------------------------------------------------------




    public void get_product_details(final String ID_PRODUCT)
    {

        apiRouter.performRequest(Apiclient.PRODUCT_DETAILS.getURL()+"/"+ID_PRODUCT,Apiclient.PRODUCT_DETAILS.getParams(), null, Request.Method.GET,Apiclient.PRODUCT_DETAILS.getCode());

    }

    //----------------------------------------------------------------------------------------------


    public void add_rate(final String id_user, final String id_product, final String rate_number)
    {

        apiRouter.performRequest(Apiclient.ADD_RATE.getURL()+"/"+id_user+"/"+id_product+"/"+rate_number,Apiclient.ADD_RATE.getParams(), null, Request.Method.GET,Apiclient.ADD_RATE.getCode());

    }

    //----------------------------------------------------------------------------------------------


    public void get_all_comments( final String id_product, final String page_number)
    {

        apiRouter.performRequest(Apiclient.ALL_COMMENTS_OF_PRODUCT.getURL()+"/"+id_product+"/"+page_number,Apiclient.ALL_COMMENTS_OF_PRODUCT.getParams(), null, Request.Method.GET,Apiclient.ALL_COMMENTS_OF_PRODUCT.getCode());

    }

    //----------------------------------------------------------------------------------------------


    public void add_comments(final String usercomment, final String id_product, final String id_user)
    {

        apiRouter.performRequest(Apiclient.ADD_COMMENTS.getURL(),Apiclient.ADD_COMMENTS.getParams(), Arrays.asList(usercomment,id_product,id_user), Request.Method.POST,Apiclient.ADD_COMMENTS.getCode());

    }

    //----------------------------------------------------------------------------------------------



    public void get_search(final String searchWord, final String id_user)
    {

        apiRouter.performRequest(Apiclient.GET_SEARCH.getURL()+"/"+searchWord+"/"+id_user,Apiclient.GET_SEARCH.getParams(), null, Request.Method.GET,Apiclient.GET_SEARCH.getCode());

    }

    //----------------------------------------------------------------------------------------------




    public void forget_password(final String mail)
    {

        apiRouter.performRequest(Apiclient.FORGET_PASSWORD.getURL(),Apiclient.FORGET_PASSWORD.getParams(), Collections.singletonList(mail), Request.Method.POST,Apiclient.FORGET_PASSWORD.getCode());

    }

    //----------------------------------------------------------------------------------------------
}