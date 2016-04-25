package com.dimon.ganwumei.mvp.presenter;

import android.support.annotation.NonNull;

import com.dimon.ganwumei.database.entities.Meizhi;
import com.dimon.ganwumei.mvp.contract.GanWuContract;

/**
 * Created by Dimon on 2016/4/25.
 */
public class GanWuPresenter implements GanWuContract.Presenter {
    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadMeizhis(boolean forceUpdate) {

    }

    @Override
    public void addNewMeizhi() {

    }

    @Override
    public void openMeizhiDetails(@NonNull Meizhi requestedMeizhi) {

    }

    @Override
    public void completeMeizhi(@NonNull Meizhi completedMeizhi) {

    }

    @Override
    public void activateMeizhi(@NonNull Meizhi activeMeizhi) {

    }

    @Override
    public void clearCompletedMeizhis() {

    }

    @Override
    public void start() {

    }
}
