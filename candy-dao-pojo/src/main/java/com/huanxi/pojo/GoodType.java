package com.huanxi.pojo;

import java.io.Serializable;

public class GoodType implements Serializable {
    private static final long serialVersionUID = 7404872971849535269L;
    private Integer typeId;

    private String title;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}