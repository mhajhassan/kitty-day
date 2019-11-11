package com.nalovma.kittyday.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nalovma.kittyday.di.util.ViewModelKey;
import com.nalovma.kittyday.pages.breeds.BreedsViewModel;
import com.nalovma.kittyday.utils.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BreedsViewModel.class)
    abstract ViewModel bindBreedsViewModel(BreedsViewModel breedsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
