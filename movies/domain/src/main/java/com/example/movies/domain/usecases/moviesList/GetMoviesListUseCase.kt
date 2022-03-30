package com.example.movies.domain.usecases.moviesList

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieList

interface GetMoviesListUseCase {
    suspend fun invoke(page: Int): Response<MovieList>
}