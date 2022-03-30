package com.example.movies.domain.usecases.moviesList

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieList
import com.example.movies.domain.repositories.MoviesRepository

class GetMoviesListUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetMoviesListUseCase {
    override suspend fun invoke(page: Int): Response<MovieList> =
        moviesRepository.getListMovies(page)
}