package com.e.popularmoviesudacity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import com.e.popularmoviesudacity.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements moviesAdapter.movieClickHandler {
    private RecyclerView mRecyclerView;
    private moviesAdapter moviesAdapter;

    private List<Movie> mMovieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMovieList  = new ArrayList<>();
        mainActivityViewModel mainActivityViewModel =
           ViewModelProviders.of(this).get(com.e.popularmoviesudacity.mainActivityViewModel.class);

        moviesAdapter = new moviesAdapter(new ArrayList<>(),this);
        mRecyclerView = findViewById(R.id.movie_recycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.setAdapter(moviesAdapter);

        mainActivityViewModel.getPopularMoviesLiveData().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movieList) {
                moviesAdapter.setAdapterMovieList(movieList);
                mMovieList= movieList;
            }
        });
        //getPopularMovieList();

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieClickListener(int position) {
        Intent moviesDetailsIntent = new Intent (this, movieDetailsActivity.class);
        moviesDetailsIntent.putExtra(movieDetailsActivity.ParceledMovie, mMovieList.get(position));
        startActivity(moviesDetailsIntent);


    }
}
