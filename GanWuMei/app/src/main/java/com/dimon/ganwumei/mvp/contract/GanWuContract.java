package com.dimon.ganwumei.mvp.contract;

import android.support.annotation.NonNull;

import com.dimon.ganwumei.database.entities.Meizhi;

import java.util.List;

/**
 * Created by Dimon on 2016/4/25.
 */
public interface GanWuContract {

    interface View {

        void setLoadingIndicator(boolean active);

        void showMeizhis(List<Meizhi> meizhis);

        void showMeizhiDetailsUi(String meizhiURL);

        void showMeizhiMarkedComplete();

        void showMeizhiMarkedActive();

        void showCompletedMeizhisCleared();

        void showLoadingMeizhisError();

        boolean isInactive();

    }

    interface UserActionsListener {

        void loadAllMeizhis(boolean forceUpdate);

        void addNewMeizhi();

        void openMeizhiDetails(@NonNull Meizhi requestedMeizhi);

        void completeMeizhi(@NonNull Meizhi completedMeizhi);

        void activateMeizhi(@NonNull Meizhi activeMeizhi);

        void clearCompletedMeizhis();
    }
}
