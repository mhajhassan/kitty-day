package com.nalovma.kittyday.pages.main;

import com.nalovma.kittyday.pages.breed_details.BreedDetailsFragment;
import com.nalovma.kittyday.pages.breeds.BreedsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {


    @ContributesAndroidInjector
    abstract BreedsFragment provideBreedsFragment();


    @ContributesAndroidInjector
    abstract BreedDetailsFragment provideBreedDetailsFragment();

}

