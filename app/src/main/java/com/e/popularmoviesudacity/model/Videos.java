package com.e.popularmoviesudacity.model;

import com.google.gson.annotations.SerializedName;

public class Videos {

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
}
