package com.dimon.ganwumei.database.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dimon on 2016/3/23.
 */
public class DayGanhuo {

    @SerializedName("iOS")
    private ArrayList<Ganhuo> iosGanhuo;
    @SerializedName("Android")
    private ArrayList<Ganhuo> androidGanhuo;
    @SerializedName("瞎推荐")
    private ArrayList<Ganhuo> recommend;
    @SerializedName("福利")
    private ArrayList<Ganhuo> benefit;
    @SerializedName("休息视频")
    private ArrayList<Ganhuo> restVideo;
    @SerializedName("拓展资源")
    private ArrayList<Ganhuo> expandRes;

    public ArrayList<Ganhuo> getIosGanhuo() {
        return iosGanhuo;
    }

    public void setIosGanhuo(ArrayList<Ganhuo> iosGanhuo) {
        this.iosGanhuo = iosGanhuo;
    }

    public ArrayList<Ganhuo> getAndroidGanhuo() {
        return androidGanhuo;
    }

    public void setAndroidGanhuo(ArrayList<Ganhuo> androidGanhuo) {
        this.androidGanhuo = androidGanhuo;
    }

    public ArrayList<Ganhuo> getRecommend() {
        return recommend;
    }

    public void setRecommend(ArrayList<Ganhuo> recommend) {
        this.recommend = recommend;
    }

    public ArrayList<Ganhuo> getBenefit() {
        return benefit;
    }

    public void setBenefit(ArrayList<Ganhuo> benefit) {
        this.benefit = benefit;
    }

    public ArrayList<Ganhuo> getRestVideo() {
        return restVideo;
    }

    public void setRestVideo(ArrayList<Ganhuo> restVideo) {
        this.restVideo = restVideo;
    }

    public ArrayList<Ganhuo> getExpandRes() {
        return expandRes;
    }

    public void setExpandRes(ArrayList<Ganhuo> expandRes) {
        this.expandRes = expandRes;
    }
}
