package com.liucong.wisdombj.bean;

import java.util.ArrayList;

public  class NewsCenterDataBean {
    private int id;
    private String title;
    private int type;
    private ArrayList<NewsCenterDataChildrenBean> children;
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public ArrayList<NewsCenterDataChildrenBean> getChildren() {
        return children;
    }


}
