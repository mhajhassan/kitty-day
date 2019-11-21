package com.nalovma.kittyday.di.module;

import com.nalovma.kittyday.pages.breed_details.BreedDetailsFragment;
import com.nalovma.kittyday.pages.breeds.BreedsFragment;
import com.nalovma.kittyday.pages.favorite.FavoriteFragment;
import com.nalovma.kittyday.pages.main.NavigationDrawerFragment;
import com.nalovma.kittyday.pages.public_images.PublicImagesFragment;
import com.nalovma.kittyday.pages.upload_image.UploadImageFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract BreedsFragment provideBreedsFragment();

    @ContributesAndroidInjector
    abstract BreedDetailsFragment provideBreedDetailsFragment();

    @ContributesAndroidInjector
    abstract PublicImagesFragment providePublicImagesFragment();

    @ContributesAndroidInjector
    abstract FavoriteFragment provideFavoriteFragment();

    @ContributesAndroidInjector
    abstract UploadImageFragment provideUploadImageFragment();

    @ContributesAndroidInjector
    abstract NavigationDrawerFragment provideNavigationDrawerFragment();
}

