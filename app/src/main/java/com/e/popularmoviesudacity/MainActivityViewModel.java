package com.e.popularmoviesudacity;



import android.app.Application;

import androidx.lifecycle.LiveData;

import androidx.lifecycle.ViewModel;

import com.e.popularmoviesudacity.model.Movie;

import java.util.List;

public class MainActivityViewModel extends ViewModel {


    private final com.e.popularmoviesudacity.movieDataSource movieDataSource;
    private MoviesRepository moviesRepository;

    public MainActivityViewModel(Application application) {
        movieDataSource = new movieDataSource();
        moviesRepository = new MoviesRepository(application);

    }


        LiveData<List<Movie>> getPopularMoviesLiveData(){
        return movieDataSource.getPopularMovies();
        }


        LiveData<List<Movie>> getTopRatedList(){
        return movieDataSource.getTopRated();
    }

    LiveData<List<Movie>> getFavoritesList(){
       return moviesRepository.getAllFavorites();
    }

}
