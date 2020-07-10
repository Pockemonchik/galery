package com.example.galery.ui.home;
import com.orm.SugarRecord;

import java.util.List;

public class PictureDb extends SugarRecord{
    private String name;
    private String disc;
    private int image;
    private List<Point> points;
    public PictureDb(){

    }
//    public PictureDb(String name, String disc, int image,List<Point> points){
//        this.points=points;
//        this.name=name;
//        this.disc = disc;
//        this.image = image;
//    }
public PictureDb(String name, String disc, int image){

        this.name=name;
        this.disc = disc;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisc() {
        return this.disc;
    }

    public void setdisc(String disc) {
        this.disc = disc;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public List<Point> getPoints(){
        return this.points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
