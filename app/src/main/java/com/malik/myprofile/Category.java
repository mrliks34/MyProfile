package com.malik.myprofile;

public class Category {
    private String title;
    private int iconResId;
    private String detailType;

    public Category(String title, int iconResId, String detailType) {
        this.title = title;
        this.iconResId = iconResId;
        this.detailType = detailType;
    }

    public String getTitle() {
        return title;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getDetailType() {
        return detailType;
    }
}