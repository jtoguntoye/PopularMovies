package com.e.popularmoviesudacity;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.popularmoviesudacity.model.Reviews;
import com.e.popularmoviesudacity.model.Videos;

import java.util.List;

// this viewModel class is used to get the video trailers for each movie from the data source class
class DetailsViewModel extends ViewModel {


    private final com.e.popularmoviesudacity.movieDataSource movieDataSource;
    private int movieID;

    public DetailsViewModel(int movieID) {
        this.movieID = movieID;
        movieDataSource = new movieDataSource();

        //reviewList= movieDataSource.getMovieReviews(movieID);
        //Log.d("MODEL TAG","VideoList size:"+(videosList.size()));
        //Log.d("TRAILERVIEWMODEL", String.valueOf(videosList.size()));
    }

   public LiveData<List<Videos>> getVideosList(){
     return  movieDataSource.getMovieTrailers(movieID);


    }

    public LiveData<List<Reviews>> getReviewList(){
        return movieDataSource.getMovieReviews(movieID);
    }
}
