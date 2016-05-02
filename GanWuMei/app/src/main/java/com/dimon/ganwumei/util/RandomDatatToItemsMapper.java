package com.dimon.ganwumei.util;

import com.dimon.ganwumei.database.RandomData;
import com.dimon.ganwumei.database.entities.Item;
import com.dimon.ganwumei.database.entities.News;
import com.dimon.ganwumei.network.ApiException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.functions.Func1;

/**
 * Created by Dimon on 2016/5/2.
 */
public class RandomDatatToItemsMapper implements Func1<RandomData, List<Item>> {
    private static RandomDatatToItemsMapper INSTANCE = new RandomDatatToItemsMapper();

    public RandomDatatToItemsMapper() {
    }

    public static RandomDatatToItemsMapper getInstance() {
        return INSTANCE;
    }
    @Override
    public List<Item> call(RandomData randomData) {
        if (randomData.isError()) {
            throw new ApiException(100);
        }
        List<News> ganwus = randomData.results;
        List<Item> items = new ArrayList<>(ganwus.size());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for (News ganwu : ganwus){
            Item item = new Item();
            try {
                Date date = inputFormat.parse(ganwu.getCreatedAt());
                item.date = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                item.date = "unknown date";
            }
            item.setUrl(ganwu.getUrl());
            item.setWho(ganwu.getWho());
            item.setDescription(ganwu.getDesc());
            items.add(item);
        }
        return items;
    }
}
