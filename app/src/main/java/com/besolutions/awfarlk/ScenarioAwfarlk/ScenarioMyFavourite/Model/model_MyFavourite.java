package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyFavourite.Model;

public class model_MyFavourite {

    private String txtTitle;
    private String txtPrice;
    private int imgSubItem;
    private float ratingStar;

    public model_MyFavourite(String txtTitle, String txtPrice, int imgSubItem, float ratingStar) {
        this.txtTitle = txtTitle;
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
