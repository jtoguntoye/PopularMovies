package com.e.popularmoviesudacity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.e.popularmoviesudacity.model.Videos;

import java.util.List;
// this viewModel class is used to get the video trailers for each movie from the data source class
public class detailsViewModel extends ViewModel {

    private List<Videos> videosList;

    public detailsViewModel(int movieID) {
        movieDataSource movieDataSource = new movieDataSource();
        videosList = movieDataSource.getMovieTrailers(movieID);
        //Log.d("TRAILERVIEWMODEL", String.valueOf(videosList.size()));
    }

    public List<Videos> getVideosList(){
        return videosList;
    }
}
