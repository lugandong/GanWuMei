package com.dimon.ganwumei;

import android.app.Application;
import android.content.Context;

import com.dimon.ganwumei.injector.components.AppComponent;
import com.dimon.ganwumei.injector.components.DaggerAppComponent;
import com.dimon.ganwumei.injector.modules.ApiModule;
import com.dimon.ganwumei.injector.modules.AppModule;
import com.socks.library.KLog;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 *
 * Created by Dimon on 2016/3/2.
 */
public class MyApplication extends Application {

    public static Context AppContenxt;
    public static String version;
    //调试模式(打印日志)？
    private static boolean DEBUG = true;

    private AppComponent appComponent;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppContenxt=getApplicationContext();
        version="v1.0.0";
        KLog.init(DEBUG);
        initializeInjector();
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }

    private void initializeInjector() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(this))
                .build();
    }

}