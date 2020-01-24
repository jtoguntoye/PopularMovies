package com.e.popularmoviesudacity.model;

import com.google.gson.annotations.SerializedName;

public class Reviews {


    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;


    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }




}
