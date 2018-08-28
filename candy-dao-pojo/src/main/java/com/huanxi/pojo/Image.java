package com.huanxi.pojo;

import java.io.Serializable;

public class Image implements Serializable {
    private static final long serialVersionUID = 6984835889944570448L;
    private Integer imageId;

    private Integer goodId;

    private String src;

    public Image(Integer goodId, String src) {
        this.goodId = goodId;
        this.src = src;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }
}