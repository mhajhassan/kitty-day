package com.nalovma.kittyday.data.rest;

import com.nalovma.kittyday.data.model.Breed;

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
}