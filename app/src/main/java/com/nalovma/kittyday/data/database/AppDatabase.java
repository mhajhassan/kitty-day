package com.nalovma.kittyday.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nalovma.kittyday.pages.favorite.FavoriteImagesDao;
import com.nalovma.kittyday.data.model.PublicImage;

@Database(entities = {PublicImage.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavoriteImagesDao publicImagesDao();
}
