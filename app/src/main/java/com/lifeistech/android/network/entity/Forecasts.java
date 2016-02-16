package com.lifeistech.android.network.entity;

import android.media.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rild on 16/02/16.
 */
public class Forecasts {
    private String dateLabel;
    private String telop;
    private String date;
    private Image image;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The dateLabel
     */
    public String getDateLabel() {
        return dateLabel;
    }

    /**
     *
     * @param dateLabel
     * The dateLabel
     */
    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel;
    }

    /**
     *
     * @return
     * The telop
     */
    public String getTelop() {
        return telop;
    }

    /**
     *
     * @param telop
     * The telop
     */
    public void setTelop(String telop) {
        this.telop = telop;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The temperature
     */

    /**
     *
     * @param temperature
     * The temperature
     */

    /**
     *
     * @return
     * The image
     */
    public Image getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
