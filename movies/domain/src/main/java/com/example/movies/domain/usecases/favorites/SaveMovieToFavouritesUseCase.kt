package com.example.movies.domain.usecases.favorites

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie

interface SaveMovieToFavouritesUseCase {
    suspend fun invoke(movie: Movie): Response<Unit>
}