package com.dimon.ganwumei.internal.components;

import android.app.Activity;

import com.dimon.ganwumei.features.base.BaseFragment;
import com.dimon.ganwumei.features.newsfeed.MainActivity;
import com.dimon.ganwumei.internal.ActivityScope;
import com.dimon.ganwumei.internal.modules.MainActivityModule;

import dagger.Component;

/**
 * Created by Dimon on 2016/3/18.
 */
@ActivityScope
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface MainActivityComponent {
    MainActivity inject(MainActivity mainActivity);
    BaseFragment inject(BaseFragment baseFragment);

    Activity Activity();
}
