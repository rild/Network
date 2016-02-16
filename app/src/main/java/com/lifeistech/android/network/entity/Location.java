package com.lifeistech.android.network.entity;

/**
 * Created by rild on 16/02/16.
 */

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Location {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("prefecture")
    @Expose
    private String prefecture;

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area The area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return The prefecture
     */
    public String getPrefecture() {
        return prefecture;
    }

    /**
     * @param prefecture The prefecture
     */
    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

}
