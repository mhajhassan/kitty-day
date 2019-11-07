package com.nalovma.kittyday.remote;

import com.nalovma.kittyday.model.Breed;
import com.nalovma.kittyday.model.Category;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CatApi {

    @GET("breeds")
    Observable<List<Breed>> getBreeds();

    @GET("categories")
    Observable<List<Category>> getCategories();
}
