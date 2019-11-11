package com.nalovma.kittyday.pages.breeds;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nalovma.kittyday.data.model.Breed;
import com.nalovma.kittyday.data.rest.CatRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BreedsViewModel extends ViewModel {

    private final CatRepository catRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<Breed>> breedsLivedata = new MutableLiveData<>();
    private final MutableLiveData<Boolean> breedsLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public BreedsViewModel(CatRepository catRepository) {
        this.catRepository = catRepository;
        disposable = new CompositeDisposable();
        fetchBreeds();
    }

    public LiveData<List<Breed>> getBreedsLivedata() {
        return breedsLivedata;
    }

    public LiveData<Boolean> getBreedsLoadError() {
        return breedsLoadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    private void fetchBreeds() {
        loading.setValue(true);
        disposable.add(catRepository.getBreeds().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Breed>>() {
                    @Override
                    public void onSuccess(List<Breed> breeds) {
                        breedsLoadError.setValue(false);
                        breedsLivedata.setValue(breeds);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        breedsLoadError.setValue(true);
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
