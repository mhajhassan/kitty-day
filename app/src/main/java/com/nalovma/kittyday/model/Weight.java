package com.nalovma.kittyday.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weight {

    @SerializedName("imperial")
    @Expose
    public String imperial;
    @SerializedName("metric")
    @Expose
    public String metric;

}