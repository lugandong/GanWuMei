package com.dimon.ganwumei.injector.modules;

import android.content.Context;

import com.dimon.ganwumei.injector.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * 把activity暴露给相关联的类。
 * Created by Dimon on 2016/3/20.
 */
@Module
public class ActivityModule {
    private final Context mContext;

    public ActivityModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @ActivityScope
    Context provideActivityContext() {
        return mContext;
    }

}
