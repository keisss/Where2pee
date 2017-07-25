package cn.keiss.where2pee.bean;

/**
 * Created by hekai on 2017/7/18.
 * recyclerView的item
 */

public class ToiletListItem {
    private String Id;
    private String picUrl;
    private int ratingNum;
    private int thumbUpNum;
    private int thumbDownNum;
    private boolean isFree;
    private boolean isHavePaper;
    private boolean isWash;
    private int distance;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getRatingNum() {
        return ratingNum;
    }

    public void setRatingNum(int ratingNum) {
        this.ratingNum = ratingNum;
    }

    public int getThumbUpNum() {
        return thumbUpNum;
    }

    public void setThumbUpNum(int thumbUpNum) {
        this.thumbUpNum = thumbUpNum;
    }

    public int getThumbDownNum() {
        return thumbDownNum;
    }

    public void setThumbDownNum(int thumbDownNum) {
        this.thumbDownNum = thumbDownNum;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public boolean isHavePaper() {
        return isHavePaper;
    }

    public void setHavePaper(boolean havePaper) {
        isHavePaper = havePaper;
    }

    public boolean isWash() {
        return isWash;
    }

    public void setWash(boolean wash) {
        isWash = wash;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
