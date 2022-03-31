package com.example.movies.domain.usecases

import com.example.kotlinhelpers.Response

interface DeleteMovieFromFavouritesUseCase {
    suspend fun invoke(id: Int): Response<Unit>
}