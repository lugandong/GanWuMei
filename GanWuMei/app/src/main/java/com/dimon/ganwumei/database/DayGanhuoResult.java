package com.dimon.ganwumei.database;

import com.dimon.ganwumei.database.entity.DayGanhuo;

import java.util.ArrayList;

/**
 * Created by Dimon on 2016/3/23.
 */
public class DayGanhuoResult extends BaseResult{
    private DayGanhuo results;
    private ArrayList<String> category;

    public DayGanhuo getResults() {
        return results;
    }

    public void setResults(DayGanhuo results) {
        this.results = results;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public void setCategory(ArrayList<String> category) {
        this.category = category;
    }
}
