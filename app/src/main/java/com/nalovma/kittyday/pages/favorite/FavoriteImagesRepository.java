package com.nalovma.kittyday.pages.favorite;

import com.nalovma.kittyday.data.model.PublicImage;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class FavoriteImagesRepository {
    private final FavoriteImagesDao favoriteImagesDao;

    @Inject
    public FavoriteImagesRepository(FavoriteImagesDao favoriteImagesDao) {
        this.favoriteImagesDao = favoriteImagesDao;
    }

    public Single<List<PublicImage>> getAllFavoriteImages() {
        return favoriteImagesDao.getAllFavouriteImages();
    }

    public void insertFavoriteImage(PublicImage publicImage) {
        favoriteImagesDao.insertFavoriteImage(publicImage);
    }

    public void deleteFavoriteImage(PublicImage publicImage) {
        favoriteImagesDao.removeFavoriteImage(publicImage);
    }

    public boolean isFavoriteImage(long id) {
        return favoriteImagesDao.isFavouriteImage(id) > 0;
    }

}
