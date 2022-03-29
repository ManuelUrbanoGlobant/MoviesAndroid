package com.example.movies.domain.usecases.moviesList

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.repositories.MoviesRepository

class GetMoviesListUseCaseImpl(private val moviesRepository: MoviesRepository) : GetMoviesListUseCase {
    override suspend fun invoke(page: Int): Response<List<Movie>> = moviesRepository.getListMovies(page)
}