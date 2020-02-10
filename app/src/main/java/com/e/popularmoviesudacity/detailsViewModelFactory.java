package com.e.popularmoviesudacity;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

// viewModel Factory class to allow us pass custom constructor to the detailsActivity viewModel
public class detailsViewModelFactory implements ViewModelProvider.Factory {

    private  int movieID;
    private Application application;
    public detailsViewModelFactory(Application application, int movieID) {
        this.application = application;
        this.movieID = movieID;
    }


    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(DetailsViewModel.class)){

        return (T) new DetailsViewModel(application,movieID);
    }
        throw new IllegalArgumentException("unknown viewModel class");
    }
}
