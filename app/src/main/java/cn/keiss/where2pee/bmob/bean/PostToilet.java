package cn.keiss.where2pee.bmob.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by hekai on 2017/7/12.
 */

public class PostToilet extends BmobObject {
    private User user;
    private Integer dislikeNumber;
    private String geoHash;
    private Boolean isFree;
    private Boolean isPaper;
    private Boolean isUseFul;
    private Boolean isWater;
    private Double latitude;
    private Double longitude;
    private Integer likeNumber;
    private String locationDescription;
    private String markContent;
    private String openTime;
    private Integer rating;
    private Integer usedTImes;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getDislikeNumber() {
        return dislikeNumber;
    }

    public void setDislikeNumber(Integer dislikeNumber) {
        this.dislikeNumber = dislikeNumber;
    }

    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public Boolean getPaper() {
        return isPaper;
    }

    public void setPaper(Boolean paper) {
        isPaper = paper;
    }

    public Boolean getUseFul() {
        return isUseFul;
    }

    public void setUseFul(Boolean useFul) {
        isUseFul = useFul;
    }

    public Boolean getWater() {
        return isWater;
    }

    public void setWater(Boolean water) {
        isWater = water;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getMarkContent() {
        return markContent;
    }

    public void setMarkContent(String markContent) {
        this.markContent = markContent;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getUsedTImes() {
        return usedTImes;
    }

    public void setUsedTImes(Integer usedTImes) {
        this.usedTImes = usedTImes;
    }

    public Integer getBadTimes() {
        return badTimes;
    }

    public void setBadTimes(Integer badTimes) {
        this.badTimes = badTimes;
    }

    private Integer badTimes;





}
