package com.example.movies.domain.repositories

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.entities.MovieDetail
import kotlinx.coroutines.flow.Flow
import com.example.movies.domain.entities.MovieList

interface MoviesRepository {
    suspend fun getListMovies(page: Int?): Response<MovieList>
    suspend fun getDetailMovie(id: Int): Response<MovieDetail>

    suspend fun saveMovieToFavourites(movie: Movie): Response<Unit>

    suspend fun deleteMovieFromFavourites(id: Int): Response<Unit>

    suspend fun getFavouritesMovies(): Response<Flow<List<Movie>>>
}