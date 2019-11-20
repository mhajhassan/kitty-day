package com.nalovma.kittyday.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "favorite_table")
public class PublicImage {

    @Ignore
    @SerializedName("breeds")
    @Expose
    private List<Breed> breeds = null;
    @SerializedName("height")
    @Expose
    private Integer height;


    @NonNull
    @PrimaryKey
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

    @NotNull
    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Integer getWidth() {
        return width;
    }

    public PublicImage(Integer height, @NonNull String id, String url, Integer width) {
        this.height = height;
        this.id = id;
        this.url = url;
        this.width = width;
    }
}
