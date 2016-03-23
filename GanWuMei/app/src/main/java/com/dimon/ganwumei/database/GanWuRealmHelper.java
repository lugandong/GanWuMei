package com.dimon.ganwumei.database;

import android.content.Context;

import java.io.FileNotFoundException;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Dimon on 2016/3/23.
 */
public class GanWuRealmHelper {
    public static Realm getRealm(Context context){
        Realm realm;
        RealmConfiguration configuration = new RealmConfiguration.Builder(context)
                .name("gank.io")
                .schemaVersion(0)
                .build();
        try {
            Realm.migrateRealm(configuration, new GanWuMigration());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        realm = Realm.getInstance(configuration);
        return realm;
    }
}
