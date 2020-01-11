package com.e.popularmoviesudacity;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.e.popularmoviesudacity.model.Movie;

import java.util.List;

public class mainActivityViewModel extends ViewModel {

    private LiveData<List<Movie>> popularMoviesLiveData;
    private  movieDataSource movieDataSource = new movieDataSource();

    public mainActivityViewModel() {
//PopularmoviesLiveData = new MutableLiveData<>();
        popularMoviesLiveData = movieDataSource.getPopularMovies();
    }


        public LiveData<List<Movie>> getPopularMoviesLiveData(){
        return popularMoviesLiveData;
        }


}
