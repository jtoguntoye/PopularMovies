package com.e.popularmoviesudacity.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


public class Reviews {

    public Reviews(){}




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


    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
