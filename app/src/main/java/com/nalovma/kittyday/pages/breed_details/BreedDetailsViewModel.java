package com.nalovma.kittyday.pages.breed_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nalovma.kittyday.data.model.Breed;
import com.nalovma.kittyday.data.model.BreedCatImage;
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
    private final MutableLiveData<List<BreedCatImage>> catImageLivedata = new MutableLiveData<>();

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

    public LiveData<List<BreedCatImage>> getCatImageLivedata() {
        return catImageLivedata;
    }


    public void fetchCatImage(String id, String page, String limit) {
        disposable.add(catRepository.getBreedImage(id, page, limit).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<BreedCatImage>>() {

                    @Override
                    public void onSuccess(List<BreedCatImage> breedCatImage) {
                        catImageLivedata.setValue(breedCatImage);
                    }

                    @Override
                    public void onError(Throwable e) {
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
