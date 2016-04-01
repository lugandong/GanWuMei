package com.dimon.ganwumei.mvp.presenters;

import com.dimon.ganwumei.mvp.views.View;

/**
 * Created by Dimon on 2016/3/22.
 */
public interface Presenter {

    void onCreate();

    void onStart();

    void onStop();

    void onPause();

    void attachView(View v);

    /**
     * 该方法控制视图的生命周期;
     * 它应该在视图(Activity和Fragment)的OnResume()时被调用;
     */
    void OnResume();

    /**
     * 该方法控制视图的生命周期;
     * 它应该在视图(Activity和Fragment)的OnDestroy()时被调用;
     */
    void OnDestroy();


}
