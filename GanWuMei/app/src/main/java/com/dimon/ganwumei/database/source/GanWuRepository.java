package com.dimon.ganwumei.database.source;

import android.support.annotation.NonNull;

import com.dimon.ganwumei.database.entities.Meizhi;

import javax.inject.Inject;

/**
 * Created by Dimon on 2016/4/26.
 */
public class GanWuRepository implements MeizhiDataSource {

    private final MeizhiDataSource mGanWuRemoteDataSource;

    private final MeizhiDataSource mGsnWuLocalDataSource;


    @Inject
    GanWuRepository(MeizhiDataSource ganWuRemoteDataSource,MeizhiDataSource gsnWuLocalDataSource){
        mGanWuRemoteDataSource = ganWuRemoteDataSource;
        mGsnWuLocalDataSource = gsnWuLocalDataSource;
    }
    @Override
    public void getMeizhis(@NonNull LoadMeizhisCallback callback) {

    }

    @Override
    public void getMeizhi(@NonNull String meizhiId, @NonNull GetMeizhiCallback callback) {

    }

    @Override
    public void saveMeizhi(@NonNull Meizhi meizhi) {

    }

    @Override
    public void completeMeizhi(@NonNull Meizhi meizhi) {

    }

    @Override
    public void completeMeizhi(@NonNull String meizhiId) {

    }

    @Override
    public void activateMeizhi(@NonNull Meizhi meizhi) {

    }

    @Override
    public void activateMeizhi(@NonNull String meizhiId) {

    }

    @Override
    public void clearCompletedMeizhis() {

    }

    @Override
    public void refreshMeizhis() {

    }

    @Override
    public void deleteAllMeizhis() {

    }

    @Override
    public void deleteMeizhi(@NonNull String meizhiId) {

    }
}
