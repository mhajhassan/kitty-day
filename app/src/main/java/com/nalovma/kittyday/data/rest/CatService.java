package com.nalovma.kittyday.data.rest;

import com.nalovma.kittyday.data.model.Breed;
import com.nalovma.kittyday.data.model.BreedCatImage;
import com.nalovma.kittyday.data.model.Category;
import com.nalovma.kittyday.data.model.PublicImage;

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
    Single<List<BreedCatImage>> getBreedImage(@Query("breed_ids") String breedId, @Query("page") String page, @Query("limit") String limit);


    @GET("images/search")
    Single<List<PublicImage>> getPublicImages(@Query("page") String page, @Query("limit") String limit, @Query("order") String order);
}
