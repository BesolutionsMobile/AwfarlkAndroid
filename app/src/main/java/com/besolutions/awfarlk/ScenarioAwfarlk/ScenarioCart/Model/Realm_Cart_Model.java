package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioCart.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Realm_Cart_Model extends RealmObject {

    private String txttitle;
    private String txtpricediscount;
    private String txtprice;
    private String txtnumberchoose;
    private float ratingStar;
    private int imghome;
    @PrimaryKey
    private int id;

    public Realm_Cart_Model(String txttitle, String txtpricediscount, String txtprice, String txtnumberchoose, float ratingStar, int imghome, int id) {
        this.txttitle = txttitle;
        this.txtpricediscount = txtpricediscount;
        this.txtprice = txtprice;
        this.txtnumberchoose = txtnumberchoose;
        this.ratingStar = ratingStar;
        this.imghome = imghome;
        this.id = id;
    }

    public Realm_Cart_Model() {
    }


    @Override
    public String toString() {
        return "Realm_Cart_Model{" +
                "txttitle='" + txttitle + '\'' +
                ", txtpricediscount='" + txtpricediscount + '\'' +
                ", txtprice='" + txtprice + '\'' +
                ", txtnumberchoose='" + txtnumberchoose + '\'' +
                ", ratingStar=" + ratingStar +
                ", imghome=" + imghome +
                ", id=" + id +
                '}';
    }


    public String getTxttitle() {
        return txttitle;
    }

    public void setTxttitle(String txttitle) {
        this.txttitle = txttitle;
    }

    public String getTxtpricediscount() {
        return txtpricediscount;
    }

    public void setTxtpricediscount(String txtpricediscount) {
        this.txtpricediscount = txtpricediscount;
    }

    public String getTxtprice() {
        return txtprice;
    }

    public void setTxtprice(String txtprice) {
        this.txtprice = txtprice;
    }

    public String getTxtnumberchoose() {
        return txtnumberchoose;
    }

    public void setTxtnumberchoose(String txtnumberchoose) {
        this.txtnumberchoose = txtnumberchoose;
    }

    public float getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(float ratingStar) {
        this.ratingStar = ratingStar;
    }

    public int getImghome() {
        return imghome;
    }

    public void setImghome(int imghome) {
        this.imghome = imghome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
