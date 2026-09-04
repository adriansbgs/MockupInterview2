package com.example.parkee.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.parkee.data.local.FavoriteMovieDao
import com.example.parkee.data.local.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}