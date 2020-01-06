package com.e.popularmoviesudacity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.e.popularmoviesudacity.model.Movie;

import java.util.List;

public class mainActivityViewModel extends ViewModel {
    private movieDataSource movieDataSource;
    private LiveData<List<Movie>> PopularmoviesLiveData;

    public mainActivityViewModel() {
        movieDataSource = new movieDataSource();
        PopularmoviesLiveData = movieDataSource.getPopularMovies();
    }

        public LiveData<List<Movie>> getPopularMoviesLiveData(){
        return PopularmoviesLiveData;
        }


}
