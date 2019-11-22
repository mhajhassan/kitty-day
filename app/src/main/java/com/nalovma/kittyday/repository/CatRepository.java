package com.nalovma.kittyday.repository;

import com.nalovma.kittyday.data.model.Breed;
import com.nalovma.kittyday.data.model.BreedCatImage;
import com.nalovma.kittyday.data.model.Category;
import com.nalovma.kittyday.data.model.PublicImage;
import com.nalovma.kittyday.data.model.UploadImageResponse;
import com.nalovma.kittyday.data.model.Vote;
import com.nalovma.kittyday.data.model.VoteResponse;
import com.nalovma.kittyday.data.rest.CatService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CatRepository {

    private final CatService catService;

    @Inject
    public CatRepository(CatService catService) {
        this.catService = catService;
    }

    public Single<List<Breed>> getBreeds() {
        return catService.getBreeds();
    }

    public Single<List<Category>> getCategories() {
        return catService.getCategories();
    }

    public Single<List<Breed>> getBreedByName(String name) {
        return catService.getBreedByName(name);
    }

    public Single<List<BreedCatImage>> getBreedImage(String breedId, String page, String limit) {
        return catService.getBreedImage(breedId, page, limit);
    }

    public Single<List<PublicImage>> getPublicImages(int page, int limit, String order) {
        return catService.getPublicImages(page, limit, order);
    }

    public Single<VoteResponse> postVote(Vote vote) {
        return catService.postVote(vote);
    }

    public Single<UploadImageResponse> uploadImage(RequestBody user_idRequestBody, MultipartBody.Part part) {
        return catService.uploadImage(user_idRequestBody, part);
    }

}
