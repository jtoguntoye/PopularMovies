package com.e.popularmoviesudacity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.e.popularmoviesudacity.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.movieClickHandler,
        SharedPreferences.OnSharedPreferenceChangeListener {
    private RecyclerView mRecyclerView;
    private MoviesAdapter moviesAdapter;

    private List<Movie> mMovieList;
    private MainActivityViewModel mainActivityViewModel;
    private SharedPreferences sharedPref;

    private MainActivityViewModelFactory factory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mMovieList  = new ArrayList<>();


        factory = new MainActivityViewModelFactory(getApplication());
        mainActivityViewModel = ViewModelProviders.of(this,factory )
                        .get(MainActivityViewModel.class);

        moviesAdapter = new MoviesAdapter(new ArrayList<>(),this);
        mRecyclerView = findViewById(R.id.movie_recycler);

        //setting the gridLayout column programmatically
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numOfColumns());
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.setAdapter(moviesAdapter);

        setUpSharedPreferences();


    }

    //helper method to determine the grid width dynamically
    private int numOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int numColumns = width/widthDivider;
        if(numColumns < 2)return 2;

        return numColumns;
    }


    //helper method to set up sharedPrefereces
    private void setUpSharedPreferences(){
        //set up shared preferences default value
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        //get sharedPreference
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        loadMoviesWithPreferences(sharedPref);
        sharedPref.registerOnSharedPreferenceChangeListener(this);
    }


    // helper method to load movies with user's preferences
    private void loadMoviesWithPreferences(SharedPreferences preferences){
        String listPref = preferences.getString( SettingsActivity.KEY_PREF_SORT_ORDER,
                getString(R.string.most_popular));

        switch(listPref){
            case "Most Popular" :
                mainActivityViewModel.getPopularMoviesLiveData().observe(this, movieList -> {
                    moviesAdapter.setAdapterMovieList(movieList);
                    mMovieList= movieList;
                    Log.d("MAIN POPULAR", String.valueOf((movieList.size())));
                });
                break;
            case "Highest Rated":
                mainActivityViewModel.getTopRatedList().observe(this, movieList -> {
                    moviesAdapter.setAdapterMovieList(movieList);
                    mMovieList=movieList;
                    Log.d("MAIN RATED", String.valueOf(movieList.size()));
                });
                break;
            case "Favorites":
                mainActivityViewModel.getFavoritesList().observe(this, movieList -> {
                    moviesAdapter.setAdapterMovieList(movieList);
                    mMovieList = movieList;
                    Log.d("FAVORITES", String.valueOf(movieList.size()));
                });
                break;

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //item clickListener callback method implementation
    @Override
    public void onMovieClickListener(int position) {
        Intent moviesDetailsIntent = new Intent (this, MovieDetailsActivity.class);
        moviesDetailsIntent.putExtra(MovieDetailsActivity.ParceledMovie, mMovieList.get(position));
        startActivity(moviesDetailsIntent);


    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if( s.equals(SettingsActivity.KEY_PREF_SORT_ORDER)){
        loadMoviesWithPreferences(sharedPreferences);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //unregister the mainActivity as an onPreferenceChangeListener
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
