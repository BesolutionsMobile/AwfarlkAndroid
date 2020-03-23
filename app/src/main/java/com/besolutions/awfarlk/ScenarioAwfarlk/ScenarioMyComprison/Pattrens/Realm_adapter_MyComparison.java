package com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Pattrens;

import com.besolutions.awfarlk.ScenarioAwfarlk.ScenarioMyComprison.Model.model_MyComparison;
import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;

public class Realm_adapter_MyComparison {
    Realm realm;


    public Realm_adapter_MyComparison(Realm realm) {
        this.realm = realm;
    }

    public void save(final model_MyComparison cartModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


//                Rcy_Cart_Model c = realm.copyToRealm(cartModel);

/*
                //int x = (int) UUID.randomUUID().getMostSignificantBits();
                //c.setId(x);
                // increment index
                Number currentIdNum = realm.where(Cart_Model.class).max("id");
                int nextId;
                if(currentIdNum == null) {
                    nextId = currentIdNum.intValue() + 1;
                } else {
                    nextId = currentIdNum.intValue() + 1;
                }

                Cart_Model z = new Cart_Model();
                z.setId(nextId);

 */

                //...
                //realm.insertOrUpdate(user); // using insert API


                Number currentIdNum = realm.where(model_MyComparison.class).max("id");
                int nextId;
                if(currentIdNum == null) {
                    nextId = 100;
                } else {
                    nextId = currentIdNum.intValue() + 1;
                }
//                Rcy_Cart_Model cart = new Rcy_Cart_Model(); // unmanaged
                cartModel.setId(nextId);
                //...
                realm.insertOrUpdate(cartModel); // using insert API
//                realm.copyToRealm(cartModel);


            }
        });

    }


    public void delete(final int i) {

        realm = Realm.getDefaultInstance();

        final RealmResults<model_MyComparison> results = realm.where(model_MyComparison.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // remove single match
                //results.deleteFirstFromRealm();
                //results.deleteLastFromRealm();

                // remove a single object


                results.deleteFromRealm(i);

                // Delete all matches
                // results.deleteAllFromRealm();
            }
        });
    }


    public ArrayList<model_MyComparison> retrieve() {
        ArrayList<model_MyComparison> cart_data = new ArrayList<>();

        realm = Realm.getDefaultInstance();
        RealmResults<model_MyComparison> cart_models = realm.where(model_MyComparison.class).findAll();

        for (model_MyComparison s : cart_models) {

            cart_data.add(s);


        }
        return cart_data;

    }

    public void delete_all() {

        realm = Realm.getDefaultInstance();

        final RealmResults<model_MyComparison> results = realm.where(model_MyComparison.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.deleteAll();

            }
        });
    }
}
