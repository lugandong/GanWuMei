package com.dimon.ganwumei.database;

import android.content.Context;

import com.dimon.ganwumei.injector.qualifier.ContextType;
import com.dimon.ganwumei.network.RestAPI;

import javax.inject.Inject;

import rx.Observable;

/**
 *
 * Created by Dimon on 2016/4/21.
 */
public class DataManager {
    private Context mContext;
    private RestAPI mRestAPI;

    @Inject
    public DataManager(@ContextType("application")Context context, RestAPI restAPI){
        this.mContext = context;
        this.mRestAPI = restAPI;
    }

    //返回image的信息
    public Observable<ImageData> getImageData(int page){
        return mRestAPI.getImageData(page);
    }

    //返回每日GanWu的信息
    public Observable<GanWuData> getGanWuData(String year,String month,String day){
        return mRestAPI.getGanWuData(year,month,day);
    }
}
