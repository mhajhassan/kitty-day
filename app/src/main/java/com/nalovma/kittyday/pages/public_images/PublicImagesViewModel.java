package com.nalovma.kittyday.pages.public_images;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.nalovma.kittyday.data.model.PublicImage;
import com.nalovma.kittyday.data.model.Vote;
import com.nalovma.kittyday.data.model.VoteResponse;
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


    public void fetchPublicImages(int page, int limit, String order) {
        loading.setValue(true);
        disposable.add(catRepository.getPublicImages(page, limit, order).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<PublicImage>>() {

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

    public void fetchPublicImages(int page, int limit, String order, String breed_ids, String category_ids) {
        loading.setValue(true);
        disposable.add(catRepository.getPublicImages(page, limit, order, breed_ids, category_ids).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<PublicImage>>() {

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

    public void postVote(Vote vote) {
        disposable.add(catRepository.postVote(vote).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<VoteResponse>() {
                    @Override
                    public void onSuccess(VoteResponse voteResponse) {
                        Log.e("message", voteResponse.getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("message", e.getLocalizedMessage());
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
