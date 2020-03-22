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

    public  void get_all_branches ()
    {

        apiRouter.performRequest(Apiclient.GET_ALL_BRANCHES.getURL(),Apiclient.GET_ALL_BRANCHES.getParams(),null, Request.Method.GET,Apiclient.GET_ALL_BRANCHES.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Add a new Ad
     */

    public  void get_all_shops_category ()
    {

        apiRouter.performRequest(Apiclient.GET_ALL_SHOPS_CATEGORY.getURL(),Apiclient.GET_ALL_SHOPS_CATEGORY.getParams(),null, Request.Method.GET,Apiclient.GET_ALL_SHOPS_CATEGORY.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */

    public void get_product_by_category_id_branch_id (final String ID_Category ,final String ID_Branch)
    {

        apiRouter.performRequest(Apiclient.GET_PRODUCT_By_CATEGORY_ID_BRANCH_ID.getURL()+"/"+ID_Category+"/"+ID_Branch,Apiclient.GET_PRODUCT_By_CATEGORY_ID_BRANCH_ID.getParams(),null, Request.Method.GET, Apiclient.GET_PRODUCT_By_CATEGORY_ID_BRANCH_ID.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */


    public void get_product_details (final String ID_Product )
    {

        apiRouter.performRequest(Apiclient.GET_PRODUCT_DETAILS.getURL()+"/"+ID_Product,Apiclient.GET_PRODUCT_DETAILS.getParams(),null, Request.Method.GET, Apiclient.GET_PRODUCT_DETAILS.getCode());

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

    public void sendOrder (final String Name,final String Street,final String Building,final String Floor,final String Flat,final String Mobile,final String Logtitude,final String Latitude,final String Notes,final String IdUser,final String IDBranch,final String Products)
    {

        apiRouter.performRequest(Apiclient.SEND_ORDER.getURL(),Apiclient.SEND_ORDER.getParams(), Arrays.asList(Name,Street,Building,Floor,Flat,Mobile,Logtitude,Latitude,Notes,IdUser,IDBranch,Products), Request.Method.POST,Apiclient.SEND_ORDER.getCode());

    }

    //----------------------------------------------------------------------------------------------




    public void faq_question ()
    {

        apiRouter.performRequest(Apiclient.FAQ_QUESTION.getURL(),Apiclient.FAQ_QUESTION.getParams(), null, Request.Method.POST,Apiclient.FAQ_QUESTION.getCode());

    }

    //----------------------------------------------------------------------------------------------




    public void select_By_City(final String City)
    {

        apiRouter.performRequest(Apiclient.Select_By_City.getURL(),Apiclient.Select_By_City.getParams(), Collections.singletonList(City), Request.Method.POST,Apiclient.Select_By_City.getCode());

    }

    //----------------------------------------------------------------------------------------------
}