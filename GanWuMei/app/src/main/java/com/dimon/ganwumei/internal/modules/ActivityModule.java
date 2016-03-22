package com.dimon.ganwumei.internal.modules;

import android.app.Activity;

import com.dimon.ganwumei.internal.ActivityScope;
import com.dimon.ganwumei.network.RestAPI;
import com.dimon.ganwumei.presenters.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dimon on 2016/3/20.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity activity() {
        return this.activity;
    }

    @Provides
    @ActivityScope
    MainActivityPresenter provideMainActivityPresenter(RestAPI apiService) {
        return new MainActivityPresenter(apiService);
    }
}
