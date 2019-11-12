package com.nalovma.kittyday.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weight {

    @SerializedName("imperial")
    @Expose
    private String imperial;
    @SerializedName("metric")
    @Expose
    private String metric;

    public String getImperial() {
        return imperial;
    }

    public String getMetric() {
        return metric;
    }
}