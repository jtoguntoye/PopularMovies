package com.e.popularmoviesudacity;



import androidx.lifecycle.LiveData;

import androidx.lifecycle.ViewModel;

import com.e.popularmoviesudacity.model.Movie;

import java.util.List;

public class MainActivityViewModel extends ViewModel {


    private final com.e.popularmoviesudacity.movieDataSource movieDataSource;

    public MainActivityViewModel() {
        movieDataSource = new movieDataSource();

    }


        LiveData<List<Movie>> getPopularMoviesLiveData(){
        return movieDataSource.getPopularMovies();
        }


        LiveData<List<Movie>> getTopRatedList(){
        return movieDataSource.getTopRated();
    }

}
