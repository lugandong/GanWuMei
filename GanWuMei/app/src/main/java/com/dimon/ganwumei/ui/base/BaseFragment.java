/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.dimon.ganwumei.ui.base;


import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.dimon.ganwumei.injector.HasComponent;
import com.dimon.ganwumei.network.ImagesFactory;
import com.dimon.ganwumei.network.RestAPI;


public abstract  class BaseFragment extends Fragment {

    public static final RestAPI sGanWuIO = ImagesFactory.getGanWuIOSingleton();
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
