package com.example.movies.domain.usecases

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieRecommendationList

interface GetMovieRecommendationsUseCase {
    suspend fun invoke(id: Int, page: Int): Response<MovieRecommendationList>
}