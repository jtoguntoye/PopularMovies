package com.e.popularmoviesudacity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.e.popularmoviesudacity.Retrofit.TmdbInterface;
import com.e.popularmoviesudacity.model.Movie;

import java.util.List;

//Repository class to obtain the network data source
public class movieRepository {
    private MutableLiveData<List<Movie>> popularMovies;
    private movieDataSource movieDataSource;

    public movieRepository() {
        popularMovies = new MutableLiveData<>();
        movieDataSource = new movieDataSource();
    }


    LiveData<List<Movie>> getPopularMovies(){

     popularMovies.postValue(movieDataSource.getPopularMovies());

     return popularMovies;
    }



}

