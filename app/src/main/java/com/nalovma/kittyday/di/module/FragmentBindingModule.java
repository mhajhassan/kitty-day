package com.nalovma.kittyday.di.module;

import com.nalovma.kittyday.pages.breed_details.BreedDetailsFragment;
import com.nalovma.kittyday.pages.breeds.BreedsFragment;
import com.nalovma.kittyday.pages.random_images.PublicImagesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract BreedsFragment provideBreedsFragment();

    @ContributesAndroidInjector
    abstract BreedDetailsFragment provideBreedDetailsFragment();

    @ContributesAndroidInjector
    abstract PublicImagesFragment provideRandomImagesFragment();
}

