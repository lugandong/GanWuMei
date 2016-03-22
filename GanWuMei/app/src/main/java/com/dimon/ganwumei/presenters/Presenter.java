package com.dimon.ganwumei.presenters;

/**
 * Created by Dimon on 2016/3/22.
 */
public interface Presenter {
    /**
     * 该方法控制视图的生命周期;
     * 它应该在视图(Activity和Fragment)的OnResume()时被调用;
     */
    void resume();

    /**
     * 该方法控制视图的生命周期;
     * 它应该在视图(Activity和Fragment)的OnPause()时被调用;
     */
    void pause();

    /**
     * 该方法控制视图的生命周期;
     * 它应该在视图(Activity和Fragment)的OnDestroy()时被调用;
     */
    void destroy();
}
