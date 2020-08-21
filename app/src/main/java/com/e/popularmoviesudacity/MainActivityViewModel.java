package com.e.popularmoviesudacity;



import android.app.Application;

import androidx.lifecycle.LiveData;

import androidx.lifecycle.ViewModel;

import com.e.popularmoviesudacity.model.Movie;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private LiveData<List<Movie>> popularMovies, topRatedMovies, favoriteMovies;


    private final com.e.popularmoviesudacity.movieDataSource movieDataSource;
    private MoviesRepository moviesRepository;

    public MainActivityViewModel(Application application) {
        movieDataSource = new movieDataSource();
        moviesRepository = new MoviesRepository(application);
        favoriteMovies = moviesRepository.getAllFavorites();
        popularMovies = movieDataSource.getPopularMovies();
        topRatedMovies = movieDataSource.getTopRated();

    }


       public LiveData<List<Movie>> getPopularMoviesLiveData(){
       return popularMovies;
        }


        public LiveData<List<Movie>> getTopRatedList(){
            return topRatedMovies;
    }

    public LiveData<List<Movie>> getFavoritesList(){
      return favoriteMovies;
    }

}
