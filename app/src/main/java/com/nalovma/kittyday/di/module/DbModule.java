package com.nalovma.kittyday.di.module;

import android.content.Context;

import androidx.room.Room;

import com.nalovma.kittyday.data.database.AppDatabase;
import com.nalovma.kittyday.pages.favorite.FavoriteImagesDao;
import com.nalovma.kittyday.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Singleton
    @Provides
    public static AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, Constants.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    public FavoriteImagesDao providePublicImagesDao(AppDatabase appDatabase) {
        return appDatabase.publicImagesDao();
    }
}
