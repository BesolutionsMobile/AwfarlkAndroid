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
    GET_ALL_BRANCHES("Branch/getAll/549834453/25598",null,3),
    GET_ALL_SHOPS_CATEGORY("Category/getAllMainCategories/549834453/25598",null,4),
    GET_PRODUCT_By_CATEGORY_ID_BRANCH_ID("Category/getCategoryProducutByBranchId/549834453/25598",null,5),
    GET_PRODUCT_DETAILS("Product/productdetails/549834453/25598", null,6),
    VIEW_PROFILE("user/view/549834453/25598",null,7),
    EDIT_PROFILE("user/editProfile/549834453/25598", Arrays.asList("name","mail","phone","id_user"),8),
    CHANGE_PASSWORD("user/update_password/549834453/25598", Arrays.asList("old_password","new_password","re_new_password","id_user"),9),
    SEND_ORDER("cart/sendOrder/549834453/25598", Arrays.asList("name","street","building","floor","flat","mobile","longitude","latitude","notes","id_user","id_branch","products"),10),
    FAQ_QUESTION("Question/View/549834453/25598", null,11),
    Select_By_City("/select_haraj_by_search_city", Collections.singletonList("city"),12);





    //--------------------------------------

    /**
     * @BASE_URL
     */

    String BASE_URL = "https://webdesign.be4em.info/rosto_api_ar/";

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
