package com.nalovma.kittyday.pages.search_images;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nalovma.kittyday.data.model.Category;
import com.nalovma.kittyday.repository.CatRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CategoriesViewModel extends ViewModel {

    private final CatRepository catRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<Category>> categoriesLivedata = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public CategoriesViewModel(CatRepository catRepository) {
        this.catRepository = catRepository;
        disposable = new CompositeDisposable();
    }

    public LiveData<Boolean> getLoadError() {
        return loadError;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<List<Category>> getCategoriesLivedata() {
        return categoriesLivedata;
    }

    public void fetchCategories() {
        loading.setValue(true);
        disposable.add(catRepository.getCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<List<Category>>() {
                    @Override
                    public void onSuccess(List<Category> categories) {
                        loadError.setValue(false);
                        categoriesLivedata.setValue(categories);
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
