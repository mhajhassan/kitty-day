package com.nalovma.kittyday.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VoteResponse {

    @SerializedName("message")
    @Expose
    private String Message;

    @SerializedName("id")
    @Expose
    private String Id;

    public String getMessage() {
        return Message;
    }

    public String getId() {
        return Id;
    }
}
