package com.e.popularmoviesudacity.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class videoResponse {
    @SerializedName("results")
    private List<Videos> videosList;

    public List<Videos> getVideosList() {
        return videosList;
    }
}
