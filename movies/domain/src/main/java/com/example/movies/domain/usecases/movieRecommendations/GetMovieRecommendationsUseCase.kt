package com.example.movies.domain.usecases.movieRecommendations

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieRecommendationList

interface GetMovieRecommendationsUseCase {
    suspend fun invoke(id: Int): Response<MovieRecommendationList>
}