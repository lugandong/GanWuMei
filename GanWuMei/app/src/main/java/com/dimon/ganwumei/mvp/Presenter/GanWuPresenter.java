package com.dimon.ganwumei.mvp.presenter;

import android.support.annotation.NonNull;

import com.dimon.ganwumei.database.entities.Meizhi;
import com.dimon.ganwumei.database.source.GanWuRepository;
import com.dimon.ganwumei.mvp.contract.GanWuContract;

import javax.inject.Inject;

/**
 * Created by Dimon on 2016/4/25.
 */
public class GanWuPresenter implements GanWuContract.UserActionsListener {
    private final GanWuRepository mGanWuRepository;

    private final GanWuContract.View mGanWuView;

    @Inject
    GanWuPresenter(GanWuRepository ganWuRepository, GanWuContract.View ganWuView){
        mGanWuRepository = ganWuRepository;
        mGanWuView = ganWuView;
    }

    @Override
    public void loadAllMeizhis(boolean forceUpdate) {
        loadGanWu(forceUpdate, true);
    }

    private void loadGanWu(boolean forceUpdate, final boolean showLoadingUI){
        if (showLoadingUI) {
            mGanWuView.setLoadingIndicator(true);
        }
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
}
