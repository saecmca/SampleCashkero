package com.mani.cashkaro.cashkaro.fragments;

/**
 * Created by Mani on 05-09-2017.
 */

public class Homemodel {
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private int image;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getViewmore() {
        return viewmore;
    }

    public void setViewmore(String viewmore) {
        this.viewmore = viewmore;
    }

    private String viewmore;
}
