package com.nalovma.kittyday.data.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublicImage {

    @SerializedName("breeds")
    @Expose
    private List<Breed> breeds = null;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("width")
    @Expose
    private Integer width;

    public List<Breed> getBreeds() {
        return breeds;
    }

    public Integer getHeight() {
        return height;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Integer getWidth() {
        return width;
    }
}
