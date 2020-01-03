package com.e.popularmoviesudacity.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// POJO class to hold the response from the TMDB API
public class MovieResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private String totalResults;

    @SerializedName("total_pages")
    private String totalPages;

    @SerializedName("results")
    private List<Movie> movies;



    public List<Movie> getMovies() {
        return movies;
    }

}
