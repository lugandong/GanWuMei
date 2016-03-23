package com.dimon.ganwumei.service;


import com.dimon.ganwumei.database.Image;


public class ImageWrapper {
    public final String url;

    public final int width;
    public final int height;

    private ImageWrapper(Image image) {
        this.url = image.getUrl();
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public static ImageWrapper from(Image image) {
        return new ImageWrapper(image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ImageWrapper)) {
            return false;
        }

        ImageWrapper image = (ImageWrapper) o;

        return url.equals(image.url);
    }

    @Override
    public int hashCode() {
        return 87 + url.hashCode();
    }
}
