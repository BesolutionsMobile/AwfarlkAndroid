package com.besolutions.awfarlk.NetworkLayer;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Apiclient
{

    /**
     * @API
     *
     * ---> 1) URL OF API METHOD
     *
     * ---> 2) ARRAY OF PARAMETERS KEYS
     *
     */

    LOGIN_USER("user/login/549834453/25598", Arrays.asList("mail", "password"),1),
    Register_Uer("user/register/549834453/25598", Arrays.asList("name", "mail","phone","password"),2),
    GET_ALL_CATEGOREY("Category/getAllMainCategories/549834453/25598",null,3),
    GET_ALL_SUB_CATEGORY("Category/getMainCategorySubCategories/549834453/25598",null,4),
    GET_All_PRODUCT_OFF_SUB_CATEGORY("Category/getAllProduct/549834453/25598",null,5),
    ADD_FAVOURITE_PRODUCT("User/addFavoriteProduct/549834453/25598", null,6),
    VIEW_PROFILE("user/view/549834453/25598",null,7),
    EDIT_PROFILE("user/editProfile/549834453/25598", Arrays.asList("name","mail","phone","id_user"),8),
    CHANGE_PASSWORD("user/update_password/549834453/25598", Arrays.asList("old_password","new_password","re_new_password","id_user"),9),
    SEND_ORDER("cart/sendOrder/549834453/25598", Arrays.asList("products","name","address","mobile","id_region","payment","id_user"),10),
    FAQ_QUESTION("Question/View/549834453/25598", null,11),
    PRODUCT_DETAILS("Product/productdetails/549834453/25598",null,12),
    FORGET_PASSWORD("user/forget_pass/549834453/25598", Collections.singletonList("mail"),13),
    DELETE_FAVOURITE_PRODUCT("User/deleteFavoriteOffers/549834453/25598", null,14),
    GET_ALL_FAVOURITE_PRODUCT("User/getFavoriteOffers/549834453/25598", null,15),
    ADD_RATE("user/addRate/549834453/25598", null,16),
    ALL_COMMENTS_OF_PRODUCT("comments/view_offer_comments/549834453/25598", null,17),
    ADD_COMMENTS("comments/add_comment/549834453/25598", Arrays.asList("usercomment","product_id","id_user"),18),
    GET_SEARCH("Product/searchByName/549834453/25598", null,19);








    //--------------------------------------

    /**
     * @BASE_URL
     */

    String BASE_URL = "https://webdesign.be4em.info/awfarlkapi_ar/";

    private final String URL;
    private final List<String> params;
    private  final int code;



    Apiclient(String URL, List<String> params, int code)
    {

        this.URL = URL;
        this.params = params;
        this.code = code;
}

    public String getURL()
    {
        return BASE_URL + URL;
    }

    public List<String> getParams()
    {
        return params;
    }

    public int getCode()
    {
        return code;
    }
}
