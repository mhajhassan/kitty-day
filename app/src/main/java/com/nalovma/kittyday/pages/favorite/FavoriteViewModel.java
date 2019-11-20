package com.nalovma.kittyday.pages.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nalovma.kittyday.data.model.PublicImage;
import com.nalovma.kittyday.pages.favorite.FavoriteImagesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class FavoriteViewModel extends ViewModel {

    private final FavoriteImagesRepository repository;
    private CompositeDisposable disposable;
    private MutableLiveData<List<PublicImage>> favoriteImagesLiveData = new MutableLiveData<>();

    @Inject
    public FavoriteViewModel(FavoriteImagesRepository favoriteImagesRepository) {
        this.repository = favoriteImagesRepository;
        disposable = new CompositeDisposable();
    }

    public LiveData<List<PublicImage>> getFavoriteImagesLiveData() {
        return favoriteImagesLiveData;
    }

    public void loadFavoriteImages() {

        disposable.add(repository.getAllFavoriteImages().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<PublicImage>>() {
                    @Override
                    public void onSuccess(List<PublicImage> favoriteImages) {
                        favoriteImagesLiveData.setValue(favoriteImages);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    public void insertFavoriteImage(PublicImage publicImage) {
        repository.insertFavoriteImage(publicImage);
    }

    public void deleteFavoriteImage(PublicImage publicImage) {
        repository.deleteFavoriteImage(publicImage);
    }

    public boolean isFavorite(PublicImage publicImage) {
        return repository.isFavoriteImage(publicImage.getId());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }

}
