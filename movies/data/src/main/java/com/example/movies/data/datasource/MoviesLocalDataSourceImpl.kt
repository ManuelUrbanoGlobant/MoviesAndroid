package com.example.movies.data.datasource

import com.example.movies.data.db.MovieDao
import com.example.movies.data.entities.orm.MovieORM
import kotlinx.coroutines.flow.Flow

class MoviesLocalDataSourceImpl(
    private val movieDao: MovieDao
) : MoviesLocalDataSource {

    override suspend fun saveMovieToFavourites(movie: MovieORM) {
        movieDao.saveMovie(movie)
    }

    override fun getFavouritesMovies(): Flow<List<MovieORM>> {
        return movieDao.getFavouritesMovies()
    }

    override suspend fun deleteFromFavourites(id: Int) {
        movieDao.deleteMovie(id)
    }
}