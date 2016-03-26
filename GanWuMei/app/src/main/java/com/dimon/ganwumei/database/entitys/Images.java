/*
 *
 * Copyright (C) 2015 Drakeet gmail.com>
 * Copyright (C) 2015 GuDong <maoruibin9035@gmail.com>
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

package com.dimon.ganwumei.database.entitys;

import java.util.Date;

/**
 *
 * Created by Dimon on 2016/3/26.
 */
public class Images extends Soul{

    /**
     *  "createdAt": "2016-03-24T12:35:23.841Z",
     *  "desc": "3.24",
     *  "publishedAt": "2016-03-25T11:23:49.570Z",
     *  "source": "chrome",
     *  "type": "福利",
     *  "url": "http://ww1.sinaimg.cn/large/7a8aed7bjw1f27uhoko12j20ez0miq4p.jpg",
     *  "used": true,
     *  "who": "张涵宇"
     */
    public boolean used;
    public String type;
    public String url;
    public String who;
    public String desc;
    public Date createdAt;
    public Date publishedAt;
    public Date updatedAt;
    public int imageWidth;
    public int imageHeight;

}
