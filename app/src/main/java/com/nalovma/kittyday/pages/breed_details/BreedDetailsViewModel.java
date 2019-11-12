package com.nalovma.kittyday.pages.breed_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nalovma.kittyday.data.model.Breed;
import com.nalovma.kittyday.data.model.CatImage;
import com.nalovma.kittyday.repository.CatRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BreedDetailsViewModel extends ViewModel {

    private final CatRepository catRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<Breed> selectedBreed = new MutableLiveData<>();
    private final MutableLiveData<List<CatImage>> catImageLivedata = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public BreedDetailsViewModel(CatRepository catRepository) {
        this.catRepository = catRepository;
        disposable = new CompositeDisposable();
    }

    public LiveData<Breed> getSelectedBreed() {
        return selectedBreed;
    }

    public void setSelectedBreed(Breed breed) {
        selectedBreed.setValue(breed);
    }

    public LiveData<List<CatImage>> getCatImageLivedata() {
        return catImageLivedata;
    }

    public LiveData<Boolean> getLoadError() {
        return loadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public void fetchCatImage(String id) {
        loading.setValue(true);
        disposable.add(catRepository.getBreedImage(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<CatImage>>() {

                    @Override
                    public void onSuccess(List<CatImage> catImage) {
                        loadError.setValue(false);
                        catImageLivedata.setValue(catImage);
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
