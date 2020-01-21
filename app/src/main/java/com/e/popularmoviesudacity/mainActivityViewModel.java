package com.e.popularmoviesudacity;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.popularmoviesudacity.model.Movie;

import java.util.List;

public class mainActivityViewModel extends ViewModel {

    private LiveData<List<Movie>> popularMoviesLiveData;
    private LiveData<List<Movie>> topRatedList;
    private final com.e.popularmoviesudacity.movieDataSource movieDataSource;

    public mainActivityViewModel() {
//PopularmoviesLiveData = new MutableLiveData<>();
        movieDataSource = new movieDataSource();

    }


        LiveData<List<Movie>> getPopularMoviesLiveData(){
        return movieDataSource.getPopularMovies();
        }


        LiveData<List<Movie>> getTopRatedList(){
        return movieDataSource.getTopRated();
    }

}
