package com.example.movies.domain.usecases

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieRecommendationList
import com.example.movies.domain.repositories.MoviesRepository

class GetMovieRecommendationsUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetMovieRecommendationsUseCase {

    override suspend fun invoke(id: Int, page: Int): Response<MovieRecommendationList> =
        moviesRepository.getRecommendedMovies(id, page)
}