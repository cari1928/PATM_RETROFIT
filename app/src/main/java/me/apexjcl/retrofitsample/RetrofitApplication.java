package me.apexjcl.retrofitsample;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by radog on 04/05/2017.
 */

public class RetrofitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }

}
