package com.e.popularmoviesudacity.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {

    @SerializedName("results")
    private List<Reviews> ReviewsList;


    public List<Reviews> getReviewsList() {
        return ReviewsList;
    }
}
