package com.lifeistech.android.network.entity;

/**
 * Created by rild on 16/02/16.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Example {

    @SerializedName("pinpointLocations")
    @Expose
    private List<PinpointLocation> pinpointLocations = new ArrayList<PinpointLocation>();
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("forecasts")
    @Expose
    private List<Forecast> forecasts = new ArrayList<Forecast>();
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("publicTime")
    @Expose
    private String publicTime;
    @SerializedName("copyright")
    @Expose
    private Copyright copyright;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private Description description;

    /**
     *
     * @return
     * The pinpointLocations
     */
    public List<PinpointLocation> getPinpointLocations() {
        return pinpointLocations;
    }

    /**
     *
     * @param pinpointLocations
     * The pinpointLocations
     */
    public void setPinpointLocations(List<PinpointLocation> pinpointLocations) {
        this.pinpointLocations = pinpointLocations;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     * The forecasts
     */
    public List<Forecast> getForecasts() {
        return forecasts;
    }

    /**
     *
     * @param forecasts
     * The forecasts
     */
    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    /**
     *
     * @return
     * The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The publicTime
     */
    public String getPublicTime() {
        return publicTime;
    }

    /**
     *
     * @param publicTime
     * The publicTime
     */
    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    /**
     *
     * @return
     * The copyright
     */
    public Copyright getCopyright() {
        return copyright;
    }

    /**
     *
     * @param copyright
     * The copyright
     */
    public void setCopyright(Copyright copyright) {
        this.copyright = copyright;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The description
     */
    public Description getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(Description description) {
        this.description = description;
    }

}