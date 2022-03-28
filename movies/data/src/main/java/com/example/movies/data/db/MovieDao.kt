package com.example.movies.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.data.entities.orm.MovieORM
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movieDTO: MovieORM)

    @Query("SELECT * FROM movie")
    fun getFavouritesMovies(): Flow<List<MovieORM>>

    @Query("DELETE FROM movie WHERE id=:id")
    suspend fun deleteMovie(id: Int)
}