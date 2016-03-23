package com.dimon.ganwumei.database.entitys;

import java.io.Serializable;

/**
 * Created by Dimon on 2016/3/23.
 */
public class News implements Serializable {
    //新闻标题，内容，图片
    private String title;
    private String desc;
    private int photoId;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public News(String name, String age, int photoId) {
        this.title = name;
        this.desc = age;
        this.photoId = photoId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getDesc() {
        return desc;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getTitle() {
        return title;
    }
}