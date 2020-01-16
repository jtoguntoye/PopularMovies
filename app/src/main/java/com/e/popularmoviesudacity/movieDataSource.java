package com.e.popularmoviesudacity;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.e.popularmoviesudacity.Retrofit.TmdbInterface;
import com.e.popularmoviesudacity.Retrofit.retrofitInstance;
import com.e.popularmoviesudacity.model.Movie;
import com.e.popularmoviesudacity.model.MovieResponse;
import com.e.popularmoviesudacity.model.Videos;
import com.e.popularmoviesudacity.model.videoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.e.popularmoviesudacity.Retrofit.retrofitInstance.getRetrofitInstance;

//class to get the movies from the API using retrofit
public class movieDataSource {

    private MutableLiveData<List<Videos>> videosList;
    private TmdbInterface tmdbInterface;
    private MutableLiveData<List<Movie>> popularMoviesList;

    //mutableLiveData to hold highest rated movies
    private MutableLiveData<List<Movie>> topRatedMovieList;

    public movieDataSource() {
        popularMoviesList = new MutableLiveData<>();
        topRatedMovieList = new MutableLiveData<>();
        tmdbInterface = getRetrofitInstance();
        videosList = new MutableLiveData<>();
    }
    //helper method to get list of popular movies
    public LiveData<List<Movie>> getPopularMovies(){
     Call<MovieResponse> popularMoviesCall =
      tmdbInterface.getPopularMovies(BuildConfig.TMDB_API_KEY); //add your TMDB apiKey here

     popularMoviesCall.enqueue(new Callback<MovieResponse>() {
       @Override
        public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response)
        {
            if(response.body()!=null) {
          //popularMoviesList = response.body().getMovies();
                popularMoviesList.postValue(response.body().getMovies());
           Log.d("DATASOURCE", "SIZE:"+ response.body().getMovies().size());
            }
            else {
                Log.d("DATASOURCE:", "Response is null");
            }

        }

       @Override
          public void onFailure(Call<MovieResponse> call, Throwable t)
         {
          t.printStackTrace();
         }

     });
    return popularMoviesList;
    }



        //helper method to get top rated movies
        public LiveData<List<Movie>> getTopRated(){

        Call<MovieResponse> topRatedMoviesCall =
                tmdbInterface.getTopRated(BuildConfig.TMDB_API_KEY);

        topRatedMoviesCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.body()!= null){
                    topRatedMovieList.postValue(response.body().getMovies());
                }

                else {Log.d("TOPRATED", "Response is null");
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });

    return topRatedMovieList;
    }




        //helper method to get movie trailers
        public  LiveData<List<Videos>> getMovieTrailers(int movieId){

            Call<videoResponse> videoResponseCall = tmdbInterface.getVideo(
                    movieId,
                    BuildConfig.TMDB_API_KEY);

            videoResponseCall.enqueue(new Callback<videoResponse>() {
                @Override
                public void onResponse(Call<videoResponse> call, Response<videoResponse> response) {
                    if (response.body()!=null){
                       videosList.postValue(response.body().getVideosList());
                      Log.d("SOURCE VIDEOLIST SIZE:",
                              "size is:"+response.body().getVideosList().size());

                    }
                }

                @Override
                public void onFailure(Call<videoResponse> call, Throwable t) {

                }
            });

            return videosList;
    }
}
