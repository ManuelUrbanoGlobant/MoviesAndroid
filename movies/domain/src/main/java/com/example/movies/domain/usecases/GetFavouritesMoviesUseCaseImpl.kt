package com.example.movies.domain.usecases

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetFavouritesMoviesUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetFavouritesMoviesUseCase {
    override suspend fun invoke(): Response<Flow<List<Movie>>> =
        moviesRepository.getFavouritesMovies()
}