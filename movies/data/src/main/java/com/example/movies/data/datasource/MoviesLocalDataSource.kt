package com.example.movies.data.datasource

import com.example.movies.data.entities.orm.MovieORM
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    suspend fun saveMovieToFavourites(movie: MovieORM)

    fun getFavouritesMovies(): Flow<List<MovieORM>>

    suspend fun deleteFromFavourites(id: Int)
}