package com.e.popularmoviesudacity.Retrofit;

import com.e.popularmoviesudacity.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//Interface to declare the Retrofit methods to interact with the API
public interface TmdbInterface {

    @GET("/movie/popular")
    Call<MovieResponse> getPopularMovies(
            @Query("api_key") String apiKey
    );


}
