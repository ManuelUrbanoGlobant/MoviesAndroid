package com.example.movies.domain.usecases.moviesList

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie

interface GetMoviesListUseCase {
    suspend fun invoke(page: Int): Response<List<Movie>>
}