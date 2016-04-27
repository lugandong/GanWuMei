package com.dimon.ganwumei.mvp.presenter;

import android.support.annotation.NonNull;

import com.dimon.ganwumei.database.entities.Meizhi;
import com.dimon.ganwumei.database.source.GanWuRepository;
import com.dimon.ganwumei.database.source.MeizhiDataSource;
import com.dimon.ganwumei.mvp.contract.GanWuContract;
import com.dimon.ganwumei.util.Preconditions;

import java.util.ArrayList;
import java.util.List;

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

    /**
     *
     * @param forceUpdate 传入true时，会刷新数据
     * @param showLoadingUI 传入true时，会在UI上刷新界面
     */
    private void loadGanWu(boolean forceUpdate, final boolean showLoadingUI){
        if (showLoadingUI) {
            mGanWuView.setLoadingIndicator(true);
        }
        if (forceUpdate){
            mGanWuRepository.refreshMeizhis();
        }

        mGanWuRepository.getMeizhis(new MeizhiDataSource.LoadMeizhisCallback() {
            @Override
            public void onMeizhisLoaded(List<Meizhi> meizhis) {

                List<Meizhi> meizhiToShow = new ArrayList<Meizhi>();

                for (Meizhi meizhi: meizhis){
                    meizhiToShow.add(meizhi);
                }

                if (mGanWuView.isInactive()){
                    return;
                }

                if (showLoadingUI){
                    mGanWuView.setLoadingIndicator(false);
                }

                mGanWuView.showMeizhis(meizhiToShow);
            }

            @Override
            public void onDataNotAvailable() {
                if (mGanWuView.isInactive()){
                    return;
                }

                mGanWuView.showLoadingMeizhisError();
            }
        });
    }

    /**
     *  上拉刷新
     */
    @Override
    public void addNewMeizhi() {

    }

    @Override
    public void openMeizhiDetails(@NonNull Meizhi requestedMeizhi) {
        Preconditions.checkNotNull(requestedMeizhi, "requestedMeizhi cannot be null!");
        mGanWuView.showMeizhiDetailsUi(requestedMeizhi.getUrl());
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
