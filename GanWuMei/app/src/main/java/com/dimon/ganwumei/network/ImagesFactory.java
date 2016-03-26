/*
 * Copyright (C) 2015 Drakeet gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dimon.ganwumei.network;

import javax.inject.Inject;

/**
 * Created by Dimon on 2016/3/26.
 */
public class ImagesFactory {
    @Inject RestAPI restAPI;

    protected static final Object monitor = new Object();
    static RestAPI sGanWuIOSingleton = null;
    public static final int ImagesSize = 10;


    public static RestAPI getGanWuIOSingleton() {
        synchronized (monitor) {
            if (sGanWuIOSingleton == null) {
                sGanWuIOSingleton = new restAPI.getGanWuService();
            }
            return sGanWuIOSingleton;
        }
    }


}
