package com.lifeistech.android.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rild on 16/02/15.
 */
public class WeatherEntity {
    @Expose
    @SerializedName("pinpointLocations")
    private List<PinpointLocations> pinpointLocations;

    @Expose
    @SerializedName("forecasts")
    private List<Forecast> forecasts;

    @Expose
    @SerializedName("link")
    private String link;

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<PinpointLocations> getPinpointLocations() {
        return pinpointLocations;
    }

    public void setPinpointLocations(List<PinpointLocations> pinpointLocations) {
        this.pinpointLocations = pinpointLocations;
    }
}
