package com.dimon.ganwumei.internal.modules;

import android.app.Activity;

import com.dimon.ganwumei.internal.ActivityScope;
import com.dimon.ganwumei.network.RestAPI;
import com.dimon.ganwumei.presenters.MainActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dimon on 2016/3/18.
 */
@Module
public class MainActivityModule {
    private final Activity Activity;

    public MainActivityModule(Activity Activity) {
        this.Activity = Activity;
    }


    @Provides
    @ActivityScope
    Activity provideMainActivity() {
        return this.Activity;
    }

    @Provides
    @ActivityScope
    MainActivityPresenter provideMainActivityPresenter(RestAPI apiService) {
        return new MainActivityPresenter(apiService);
    }

}
