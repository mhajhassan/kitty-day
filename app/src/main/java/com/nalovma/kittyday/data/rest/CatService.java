package com.nalovma.kittyday.data.rest;

import com.nalovma.kittyday.data.model.Breed;
import com.nalovma.kittyday.data.model.Category;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CatService {

    @GET("breeds")
    Single<List<Breed>> getBreeds();

    @GET("categories")
    Single<List<Category>> getCategories();
}
