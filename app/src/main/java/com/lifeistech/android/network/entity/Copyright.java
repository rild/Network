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
public class Copyright {

    @SerializedName("provider")
    @Expose
    private List<Provider> provider = new ArrayList<Provider>();
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private Image_ image;

    /**
     * @return The provider
     */
    public List<Provider> getProvider() {
        return provider;
    }

    /**
     * @param provider The provider
     */
    public void setProvider(List<Provider> provider) {
        this.provider = provider;
    }

    /**
     * @return The link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The image
     */
    public Image_ getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(Image_ image) {
        this.image = image;
    }

}

