package com.dimon.ganwumei.database.rx;

import android.content.Context;
import android.database.Cursor;

import com.dimon.ganwumei.database.GanWuDbHelper;
import com.dimon.ganwumei.database.entities.Item;

import java.util.ArrayList;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * GanWu的观察者
 * Created by Dimon on 2016/4/9.
 */
public class ObservableGanWuDb {
    private PublishSubject<ArrayList<Item>> mPublishSubject; // 发表主题
    private GanWuDbHelper mDbHelper; // 数据库

    public ObservableGanWuDb(Context context) {
        mDbHelper = new GanWuDbHelper(context);
        mPublishSubject = PublishSubject.create();
    }

    // 返回观察者
    public Observable<ArrayList<Item>> getObservable() {
        Observable<ArrayList<Item>> firstObservable = Observable.fromCallable(this::getRepoList);
        return firstObservable.concatWith(mPublishSubject); // 连接发表主题
    }

    // 从数据库获得数据
    private ArrayList<Item> getRepoList() {
        mDbHelper.openForRead();
        ArrayList<Item> items = new ArrayList<>();
        Cursor c = mDbHelper.getAllGanWu();
        if (!c.moveToFirst()) {
            return items; // 返回空
        }

        do {
            // 添加数据
            items.add(new Item(
                    c.getString(GanWuDbHelper.GAN_WU_ID_COLUMN_POSITION),
                    c.getString(GanWuDbHelper.GAN_WU_URL_COLUMN_POSITION),
                    c.getString(GanWuDbHelper.GAN_WU_DESCRIPTION_COLUMN_POSITION),
                    new Item.Owner(c.getString(GanWuDbHelper.GAN_WU_OWNER_COLUMN_POSITION), "", "", "")));
        } while (c.moveToNext());
        c.close();
        mDbHelper.close();
        return items;
    }

    // 插入Repo列表
    public void insertRepoList(ArrayList<Item> items) {
        mDbHelper.open();
        mDbHelper.removeAllGanWu();
        for (Item item : items) {
            mDbHelper.addGanWu(
                    item.getDate(),
                    item.getUrl(),
                    item.getDescription(),
                    item.getWho()
            );
        }
        mDbHelper.close();
        mPublishSubject.onNext(items); // 会调用更新数据
    }
}
