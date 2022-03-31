package com.example.movies.domain.usecases.favorites

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

interface GetFavouritesMoviesUseCase {
    suspend fun invoke(): Response<Flow<List<Movie>>>
}