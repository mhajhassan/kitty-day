package com.nalovma.kittyday.data.rest;

import com.nalovma.kittyday.data.model.Breed;
import com.nalovma.kittyday.data.model.CatImage;
import com.nalovma.kittyday.data.model.Category;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CatService {

    @GET("breeds")
    Single<List<Breed>> getBreeds();

    @GET("categories")
    Single<List<Category>> getCategories();

    @GET("images/search")
    Single<List<CatImage>> getBreedImage(@Query("breed_ids") String breedId);
}
