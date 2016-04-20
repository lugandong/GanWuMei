package com.dimon.ganwumei.database.entities;

import java.util.Date;

/**
 * Created by Dimon on 2016/4/20.
 */
public class Meizhi {
    private final Date mDate;
    private final String url;
    private final String desc;

    public Meizhi(Date date, String url, String desc) {
        mDate = date;
        this.url = url;
        this.desc = desc;
    }

    public Date getDate() {
        return mDate;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }
}
