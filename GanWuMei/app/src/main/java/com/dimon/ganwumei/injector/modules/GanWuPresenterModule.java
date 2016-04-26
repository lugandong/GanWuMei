package com.dimon.ganwumei.injector.modules;

import com.dimon.ganwumei.mvp.contract.GanWuContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dimon on 2016/3/22.
 */
@Module
public class GanWuPresenterModule {

    private final GanWuContract.View mView;

    public GanWuPresenterModule(GanWuContract.View view) {
        mView = view;
    }

    @Provides
    GanWuContract.View provideGanWuContractView(){
        return mView;
    }
}
