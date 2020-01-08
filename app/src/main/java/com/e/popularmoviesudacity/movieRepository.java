package com.e.popularmoviesudacity;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.e.popularmoviesudacity.Retrofit.TmdbInterface;
import com.e.popularmoviesudacity.model.Movie;

import java.util.List;

//Repository class to obtain the network data source
public class movieRepository {
    private LiveData<List<Movie>> popularMovies;
    private movieDataSource movieDataSource;

    public movieRepository() {

        movieDataSource = new movieDataSource();
        popularMovies = movieDataSource.getPopularMovies();
    }


   public LiveData<List<Movie>> getPopularMovies(){
     Log.d("RepoPopularMovies:", "size is" + popularMovies.getValue().size());
     return popularMovies;
    }



}

