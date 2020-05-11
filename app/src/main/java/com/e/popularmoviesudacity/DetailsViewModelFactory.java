package com.e.popularmoviesudacity;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

// viewModel Factory class to allow us pass custom constructor to the detailsActivity viewModel
public class DetailsViewModelFactory implements ViewModelProvider.Factory {

    private Application application;
    public DetailsViewModelFactory(Application application) {
        this.application = application;

    }


    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(DetailsViewModel.class)){

        return (T) new DetailsViewModel(application);
    }
        throw new IllegalArgumentException("unknown viewModel class");
    }
}
