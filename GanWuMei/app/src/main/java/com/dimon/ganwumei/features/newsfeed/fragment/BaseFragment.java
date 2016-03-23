/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.dimon.ganwumei.features.newsfeed.fragment;


import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.dimon.ganwumei.internal.HasComponent;


public abstract  class BaseFragment extends Fragment {

    /**
     * show Toast Message
     * @param message
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
