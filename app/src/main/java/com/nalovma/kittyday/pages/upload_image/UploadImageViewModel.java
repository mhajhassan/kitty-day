package com.nalovma.kittyday.pages.upload_image;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nalovma.kittyday.data.model.UploadImageResponse;
import com.nalovma.kittyday.repository.CatRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadImageViewModel extends ViewModel {
    private final CatRepository catRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<Boolean> loadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public UploadImageViewModel(CatRepository catRepository) {
        this.catRepository = catRepository;
        disposable = new CompositeDisposable();
    }

    public LiveData<Boolean> getLoadError() {
        return loadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public void uploadImage(RequestBody user_idRequestBody, MultipartBody.Part part) {
        loading.setValue(true);
        disposable.add(catRepository.uploadImage(user_idRequestBody, part).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UploadImageResponse>() {
                    @Override
                    public void onSuccess(UploadImageResponse uploadImageResponse) {
                        loadError.setValue(false);
                        loading.setValue(false);
                        Log.e("message", uploadImageResponse.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadError.setValue(true);
                        loading.setValue(false);
                        Log.e("message", e.getMessage());

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
