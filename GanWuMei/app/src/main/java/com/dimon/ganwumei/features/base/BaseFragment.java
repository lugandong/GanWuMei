package com.dimon.ganwumei.features.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

/**
 * Created by Dimon on 2016/3/2.
 */
public class BaseFragment extends Fragment {
    @Inject Activity activity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activity = getActivity();
    }
}
