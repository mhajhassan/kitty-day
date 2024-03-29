package com.nalovma.kittyday.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nalovma.kittyday.pages.favorite.FavoriteViewModel;
import com.nalovma.kittyday.di.util.ViewModelKey;
import com.nalovma.kittyday.pages.breed_details.BreedDetailsViewModel;
import com.nalovma.kittyday.pages.breeds.BreedsViewModel;
import com.nalovma.kittyday.pages.public_images.PublicImagesViewModel;
import com.nalovma.kittyday.pages.search_images.CategoriesViewModel;
import com.nalovma.kittyday.pages.upload_image.UploadImageViewModel;
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
    @IntoMap
    @ViewModelKey(BreedDetailsViewModel.class)
    abstract ViewModel bindBreedDetailsViewModel(BreedDetailsViewModel breedDetailsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PublicImagesViewModel.class)
    abstract ViewModel bindRandomImagesViewModel(PublicImagesViewModel publicImagesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel.class)
    abstract ViewModel bindFavoriteViewModel(FavoriteViewModel favoriteViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UploadImageViewModel.class)
    abstract ViewModel bindUploadImageViewModel(UploadImageViewModel uploadImageViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel.class)
    abstract ViewModel bindCategoriesViewModel(CategoriesViewModel categoriesViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
