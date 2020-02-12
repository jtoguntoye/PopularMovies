package com.e.popularmoviesudacity.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


public class Videos {

    public Videos(){}



    @SerializedName("key")
    private  String key;

    public String getName() {
        return name;
    }

    @SerializedName("name")
    private String name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }
}
