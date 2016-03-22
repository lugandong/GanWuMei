package com.dimon.ganwumei.database;


import com.dimon.ganwumei.database.entity.Ganhuo;

import java.util.ArrayList;

/**
 * Created by Dimon on 2016/3/18.
 */
public class GanhuoResult extends BaseResult{
    private ArrayList<Ganhuo> results;

    public GanhuoResult(ArrayList<Ganhuo> results) {
        this.results = results;
    }

    public ArrayList<Ganhuo> getResults() {
        return results;
    }

    public void setResults(ArrayList<Ganhuo> results) {
        this.results = results;
    }
}
