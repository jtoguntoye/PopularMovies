package com.e.popularmoviesudacity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.popularmoviesudacity.model.Videos;

import java.util.ArrayList;
import java.util.List;
// this viewModel class is used to get the video trailers for each movie from the data source class
public class detailsViewModel extends ViewModel {

    private LiveData<List<Videos>> videosList;

    public detailsViewModel(int movieID) {

        movieDataSource movieDataSource = new movieDataSource();
        videosList = movieDataSource.getMovieTrailers(movieID);
        //Log.d("MODEL TAG","VideoList size:"+(videosList.size()));
        //Log.d("TRAILERVIEWMODEL", String.valueOf(videosList.size()));
    }

    public LiveData<List<Videos>> getVideosList(){
        return videosList;
    }
}
