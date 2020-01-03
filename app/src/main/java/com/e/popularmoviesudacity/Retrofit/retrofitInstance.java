package com.e.popularmoviesudacity.Retrofit;

import com.e.popularmoviesudacity.R;
import com.google.gson.Gson;

import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//create a retrofit builder class
public class retrofitInstance {

private static final String BASE_URL = "https://api.themoviedb.org/3/";
//create a singleton pattern for the Retrofit client
private static Retrofit retrofit = null;

public static TmdbInterface getRetrofitInstance(){
    if(retrofit==null){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    return retrofit.create(TmdbInterface.class);
}


}
