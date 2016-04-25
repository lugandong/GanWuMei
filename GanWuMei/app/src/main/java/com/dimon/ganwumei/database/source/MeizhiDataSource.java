package com.dimon.ganwumei.database.source;

import android.support.annotation.NonNull;

import com.dimon.ganwumei.database.entities.Meizhi;

import java.util.List;

/**
 * Created by Dimon on 2016/4/25.
 */
public interface MeizhiDataSource {
    interface LoadMeizhisCallback {

        void onMeizhisLoaded(List<Meizhi> meizhis);

        void onDataNotAvailable();
    }

    interface GetMeizhiCallback {

        void onMeizhiLoaded(Meizhi meizhi);

        void onDataNotAvailable();
    }

    void getMeizhis(@NonNull LoadMeizhisCallback callback);

    void getMeizhi(@NonNull String meizhiId, @NonNull GetMeizhiCallback callback);

    void saveMeizhi(@NonNull Meizhi meizhi);

    void completeMeizhi(@NonNull Meizhi meizhi);

    void completeMeizhi(@NonNull String meizhiId);

    void activateMeizhi(@NonNull Meizhi meizhi);

    void activateMeizhi(@NonNull String meizhiId);

    void clearCompletedMeizhis();

    void refreshMeizhis();

    void deleteAllMeizhis();

    void deleteMeizhi(@NonNull String meizhiId);
}
