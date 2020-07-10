package com.example.galery.ui.home;

import com.orm.SugarRecord;

public class Point extends SugarRecord {
    private float x,y;
    private String disc;
    public Point(){

    }
    public Point(int x,int y, String disc){
        this.x= x;
        this.y=y;
        this.disc=disc;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getDisc() {
        return disc;
    }
    public float getX() {
        return x;
    }

    public void setX(float y) {
        this.x = x;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }
}
