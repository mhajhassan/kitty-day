package com.nalovma.kittyday.pages.favorite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nalovma.kittyday.data.model.PublicImage;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FavoriteImagesDao {

    @Query("SELECT * FROM favorite_table")
    Single<List<PublicImage>> getAllFavouriteImages();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavoriteImage(PublicImage publicImage);

    @Delete
    void removeFavoriteImage(PublicImage publicImage);

    @Query("SELECT count(*) FROM favorite_table where id = :id")
    int isFavouriteImage(String id);
}
