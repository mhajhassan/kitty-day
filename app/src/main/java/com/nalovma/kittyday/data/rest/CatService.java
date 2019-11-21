package com.nalovma.kittyday.data.rest;

import com.nalovma.kittyday.data.model.Breed;
import com.nalovma.kittyday.data.model.BreedCatImage;
import com.nalovma.kittyday.data.model.Category;
import com.nalovma.kittyday.data.model.PublicImage;
import com.nalovma.kittyday.data.model.UploadImageResponse;
import com.nalovma.kittyday.data.model.Vote;
import com.nalovma.kittyday.data.model.VoteResponse;

import java.util.List;


import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface CatService {

    @GET("breeds")
    Single<List<Breed>> getBreeds();

    @GET("categories")
    Single<List<Category>> getCategories();

    @GET("images/search")
    Single<List<BreedCatImage>> getBreedImage(@Query("breed_ids") String breedId, @Query("page") String page, @Query("limit") String limit);


    @GET("images/search")
    Single<List<PublicImage>> getPublicImages(@Query("page") int page, @Query("limit") int limit, @Query("order") String order);

    @POST("votes")
    Single<VoteResponse> postVote(@Body Vote vote);

    @GET("breeds/search")
    Single<List<Breed>> getBreedByName(@Query("q") String query);

    @Multipart
    @POST("images/upload")
    Single<UploadImageResponse> uploadImage(@Part("sub_id") RequestBody sub_id,
                                                @Part MultipartBody.Part file);
}
