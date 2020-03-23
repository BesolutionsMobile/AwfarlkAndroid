package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class model_MyComparison extends RealmObject {

    private String txtTitle;
    private String txtPrice;
    private int imgSubItem;
    private float ratingStar;
    @PrimaryKey
    private int id;


    public model_MyComparison(String txtTitle, String txtPrice, int imgSubItem, float ratingStar, int id) {
        this.txtTitle = txtTitle;
        this.txtPrice = txtPrice;
        this.imgSubItem = imgSubItem;
        this.ratingStar = ratingStar;
        this.id = id;
    }

    public model_MyComparison() {
    }

    @Override
    public String toString() {
        return "model_MyComparison{" +
                "txtTitle='" + txtTitle + '\'' +
                ", txtPrice='" + txtPrice + '\'' +
                ", imgSubItem=" + imgSubItem +
                ", ratingStar=" + ratingStar +
                ", id=" + id +
                '}';
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
