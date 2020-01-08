package com.e.popularmoviesudacity.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;
//class that represents each movie that is parsed from the TMDB API response
//class is made parcelable
public class Movie implements Parcelable {

    @SerializedName("poster_path")
    private String moviePosterPath;

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String movieOverview;

    @SerializedName("release_date")
    private String releaseDate;


    protected Movie(Parcel in) {
        moviePosterPath = in.readString();
        id = in.readString();
        title = in.readString();
        movieOverview = in.readString();
        releaseDate = in.readString();
    }

    //this inner class  helps to make the movies class re-creatable from a parcel
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(moviePosterPath);
    parcel.writeString(id);
    parcel.writeString(title);
    parcel.writeString(movieOverview);
    parcel.writeString(releaseDate);

    }
}
