package com.example.movies.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movies.data.entities.orm.MovieORM

@TypeConverters(Converters::class)
@Database(entities = [MovieORM::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}