package com.e.popularmoviesudacity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

// viewModel Factory class to allow us pass custom constructor to the detailsActivity viewModel
public class detailsViewModelFactory implements ViewModelProvider.Factory {

    private  int movieID;

    public detailsViewModelFactory(int movieID) {
        this.movieID = movieID;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new detailsViewModel(movieID);
    }
}
