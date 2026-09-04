package com.example.parkee.di

import android.content.Context
import androidx.room.Room
import com.example.parkee.core.database.MovieDatabase
import com.example.parkee.data.local.FavoriteMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, "parkee_movie.db").build()

    @Provides
    fun provideFavoriteMovieDao(db: MovieDatabase): FavoriteMovieDao = db.favoriteMovieDao()
}