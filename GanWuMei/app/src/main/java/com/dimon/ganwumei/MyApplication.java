package com.dimon.ganwumei;

import android.app.Application;
import android.content.Context;

import com.dimon.ganwumei.internal.components.AppComponent;
import com.dimon.ganwumei.internal.components.DaggerAppComponent;
import com.dimon.ganwumei.internal.modules.AppModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by Dimon on 2016/3/2.
 */
public class MyApplication extends Application {

    private AppComponent appComponent;

    public static MyApplication get(Context context){
        return (MyApplication)context.getApplicationContext();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(this)
                .schemaVersion(2)
                .deleteRealmIfMigrationNeeded()
                .build());

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
