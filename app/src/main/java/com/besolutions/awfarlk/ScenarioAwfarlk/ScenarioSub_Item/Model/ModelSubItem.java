package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioSub_Item.Model;

public class ModelSubItem {

    private String txtTitle;
    private String txtDiscount;
    private String txtPrice;
    private int imgSubItem;
    private float ratingStar;

    public ModelSubItem(String txtTitle, String txtDiscount, String txtPrice, int imgSubItem, float ratingStar) {
        this.txtTitle = txtTitle;
        this.txtDiscount = txtDiscount;
        this.txtPrice = txtPrice;
        this.imgSubItem = imgSubItem;
        this.ratingStar = ratingStar;
    }


    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getTxtDiscount() {
        return txtDiscount;
    }

    public void setTxtDiscount(String txtDiscount) {
        this.txtDiscount = txtDiscount;
    }

    public String getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice = txtPrice;
    }

    public int getImgSubItem() {
        return imgSubItem;
    }

    public void setImgSubItem(int imgSubItem) {
        this.imgSubItem = imgSubItem;
    }

    public float getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(float ratingStar) {
        this.ratingStar = ratingStar;
    }
}
