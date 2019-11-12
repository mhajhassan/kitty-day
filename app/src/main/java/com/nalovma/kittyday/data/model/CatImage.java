package com.nalovma.kittyday.data.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatImage {

    @SerializedName("breeds")
    @Expose
    private List<Breed> breeds = null;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("pending")
    @Expose
    private Integer pending;
    @SerializedName("approved")
    @Expose
    private Integer approved;
    @SerializedName("rejected")
    @Expose
    private Integer rejected;

    public List<Breed> getBreeds() {
        return breeds;
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

    public Integer getHeight() {
        return height;
    }

    public Integer getPending() {
        return pending;
    }

    public Integer getApproved() {
        return approved;
    }

    public Integer getRejected() {
        return rejected;
    }
}