package com.nalovma.kittyday.pages.random_images;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.nalovma.kittyday.data.model.PublicImage;
import com.nalovma.kittyday.repository.CatRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PublicImagesViewModel extends ViewModel {

    private final CatRepository catRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<PublicImage>> publicImagesLivedata = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public PublicImagesViewModel(CatRepository catRepository) {
        this.catRepository = catRepository;
        disposable = new CompositeDisposable();
    }

    public LiveData<List<PublicImage>> getPublicImagesLivedata() {
        return publicImagesLivedata;
    }


    public LiveData<Boolean> getLoadError() {
        return loadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }


    public void fetchPublicImages( int page, int limit, String order) {
        loading.setValue(true);
        disposable.add(catRepository.getPublicImages(page, limit, order).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<PublicImage>>() {

                    @Override
                    public void onSuccess(List<PublicImage> publicImages) {
                        loadError.setValue(false);
                        publicImagesLivedata.setValue(publicImages);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadError.setValue(true);
                        loading.setValue(false);
                    }
                }));

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
