package com.dimon.ganwumei.mvp.contract;

import android.support.annotation.NonNull;

import com.dimon.ganwumei.database.entities.Meizhi;
import com.dimon.ganwumei.mvp.BasePresenter;
import com.dimon.ganwumei.mvp.BaseView;

import java.util.List;

/**
 * Created by Dimon on 2016/4/25.
 */
public interface GanWuContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);

        void showMeizhis(List<Meizhi> meizhis);

        void showMeizhiDetailsUi(String taskId);

        void showMeizhiMarkedComplete();

        void showMeizhiMarkedActive();

        void showCompletedMeizhisCleared();

        void showLoadingMeizhisError();

        void showNoMeizhis();

        void showActiveFilterLabel();

        void showCompletedFilterLabel();

        void showAllFilterLabel();

        void showNoActiveMeizhis();

        void showNoCompletedMeizhis();

        void showSuccessfullySavedMessage();

        boolean isActive();

        void showFilteringPopUpMenu();
    }

    interface Presenter extends BasePresenter {
        void result(int requestCode, int resultCode);

        void loadMeizhis(boolean forceUpdate);

        void addNewMeizhi();

        void openMeizhiDetails(@NonNull Meizhi requestedMeizhi);

        void completeMeizhi(@NonNull Meizhi completedMeizhi);

        void activateMeizhi(@NonNull Meizhi activeMeizhi);

        void clearCompletedMeizhis();
    }
}
