package com.nalovma.kittyday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.nalovma.kittyday.model.Breed;
import com.nalovma.kittyday.remote.CatApi;
import com.nalovma.kittyday.remote.CatClient;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    CatApi catApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catApi = CatClient.getInstance().create(CatApi.class);
        fetchData();
    }

    private void fetchData() {
        catApi.getBreeds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Breed>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Breed> breeds) {
                        Log.e("breed",breeds.get(0).name);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("breed", Objects.requireNonNull(e.getLocalizedMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
