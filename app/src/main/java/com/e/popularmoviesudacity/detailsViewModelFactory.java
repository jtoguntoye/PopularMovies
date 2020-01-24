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


    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(DetailsViewModel.class)){
        return (T) new DetailsViewModel(movieID);
    }
        throw new IllegalArgumentException("unknown viewModel class");
    }
}
