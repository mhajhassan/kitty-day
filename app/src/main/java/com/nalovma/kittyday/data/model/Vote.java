package com.nalovma.kittyday.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vote {
    @SerializedName("image_id")
    @Expose
    public String imageId;
    @SerializedName("sub_id")
    @Expose
    public String subId;
    @SerializedName("value")
    @Expose
    public Integer value;

    public Vote(String imageId, String subId, Integer value) {
        this.imageId = imageId;
        this.subId = subId;
        this.value = value;
    }
}
