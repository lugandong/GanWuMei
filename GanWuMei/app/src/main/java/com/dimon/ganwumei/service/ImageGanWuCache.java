package com.dimon.ganwumei.service;

import com.dimon.ganwumei.database.Image;
import com.dimon.ganwumei.database.entity.Ganhuo;

import java.util.ArrayList;
import java.util.Random;

import io.realm.RealmResults;

/**
 * 图片缓存
 * Created by Dimon on 2016/3/23.
 */
public class ImageGanWuCache {
    private static ImageGanWuCache instance;

    private ArrayList<Image> mGanImageList;

    public ImageGanWuCache(){
        mGanImageList = new ArrayList<>();
    }

    public static ImageGanWuCache getIns() {
        if (null == instance) {
            synchronized (ImageGanWuCache.class) {
                if (null == instance) {
                    instance = new ImageGanWuCache();
                }
            }
        }
        return instance;
    }

    private void addGoodsToImage(Ganhuo ganhuo){
        Image image = new Image();
        Image.updateDbGoods(image,ganhuo);
        mGanImageList.add(image);
    }

    public void addAllImageGoods(ArrayList<Ganhuo> list){
        if(null != list && list.size() > 0){
            mGanImageList.clear();
            for (Ganhuo ganhuo:list){
                addGoodsToImage(ganhuo);
            }
        }
    }

    public void addAllImageGoods(RealmResults<Image> images){
        mGanImageList.clear();
        mGanImageList.addAll(images);
    }

    public ArrayList<Image> getGanImageList() {
        return mGanImageList;
    }

    public Image getImgGoodsRandom(int randomIndex){
        int size = getGanImageList().size();
        if(size == 0)return null;
        Random random = new Random();
        int randomInt = random.nextInt(size);
        if(randomInt + randomIndex >= size){
            return getGanImageList().get(randomInt);
        }
        return getGanImageList().get(randomInt + randomIndex);
    }
}
