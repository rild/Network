package com.lifeistech.android.network.entity;

/**
 * Created by rild on 16/02/16.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Forecast {

    @SerializedName("dateLabel")
    @Expose
    private String dateLabel;
    @SerializedName("telop")
    @Expose
    private String telop;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("temperature")
    @Expose
    private Temperature temperature;
    @SerializedName("image")
    @Expose
    private Image image;

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
    public Temperature getTemperature() {
        return temperature;
    }

    /**
     *
     * @param temperature
     * The temperature
     */
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

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

}
