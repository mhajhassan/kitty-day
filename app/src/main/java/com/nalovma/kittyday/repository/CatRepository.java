package com.nalovma.kittyday.repository;

import com.nalovma.kittyday.data.model.Breed;
import com.nalovma.kittyday.data.model.BreedCatImage;
import com.nalovma.kittyday.data.model.PublicImage;
import com.nalovma.kittyday.data.rest.CatService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CatRepository {

    private final CatService catService;

    @Inject
    public CatRepository(CatService catService) {
        this.catService = catService;
    }

    public Single<List<Breed>> getBreeds() {
        return catService.getBreeds();
    }

    public Single<List<BreedCatImage>> getBreedImage(String breedId, String page, String limit) {
        return catService.getBreedImage(breedId, page, limit);
    }

    public Single<List<PublicImage>> getPublicImages(int page, int limit, String order) {
        return catService.getPublicImages(page, limit, order);
    }
}
