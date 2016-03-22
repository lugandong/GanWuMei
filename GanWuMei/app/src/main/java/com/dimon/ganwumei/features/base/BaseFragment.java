package com.dimon.ganwumei.features.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by Dimon on 2016/3/2.
 */
public abstract  class BaseFragment extends Fragment {

    /**
     * show Toast Message
     * @param message
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
