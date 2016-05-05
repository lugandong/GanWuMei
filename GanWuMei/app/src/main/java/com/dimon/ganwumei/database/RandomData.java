package com.dimon.ganwumei.database;

import com.dimon.ganwumei.database.entities.News;

import java.util.List;

/**
 * 随机数据Data
 * Created by Dimon on 2016/5/2.
 */
public class RandomData {
    public List<News> results;
    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
