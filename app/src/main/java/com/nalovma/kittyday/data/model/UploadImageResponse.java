package com.nalovma.kittyday.data.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImageResponse {


    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("sub_id")
    @Expose
    public String subId;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("original_filename")
    @Expose
    public String originalFilename;
    @SerializedName("pending")
    @Expose
    public Integer pending;
    @SerializedName("approved")
    @Expose
    public Integer approved;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getSubId() {
        return subId;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public Integer getPending() {
        return pending;
    }

    public Integer getApproved() {
        return approved;
    }
}
