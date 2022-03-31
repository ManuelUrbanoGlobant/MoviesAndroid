package com.example.movies.domain.usecases.favorites

import com.example.kotlinhelpers.Response

interface DeleteMovieFromFavouritesUseCase {
    suspend fun invoke(id: Int): Response<Unit>
}