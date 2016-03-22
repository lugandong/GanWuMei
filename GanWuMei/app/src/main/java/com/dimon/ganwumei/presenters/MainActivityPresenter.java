package com.dimon.ganwumei.presenters;


import com.dimon.ganwumei.database.entity.Ganhuo;
import com.dimon.ganwumei.network.RestAPI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dimon on 2016/3/18.
 */
public class MainActivityPresenter {
    private static final int DAY_OF_MILLISECOND = 24*60*60*1000;
    private Date mCurrentDate;
    List<Ganhuo> mGanhuoList = new ArrayList<>();

    private RestAPI api;
    //private IMainView mView;

    public MainActivityPresenter(RestAPI apiService) {
        this.api = apiService;
    }

//    public void attachView(IMainView view){
//        mView = view;
//    }

    public void onCreate(){
        getData(new Date(System.currentTimeMillis()));
    }

    private void getData(final Date date) {}
}
