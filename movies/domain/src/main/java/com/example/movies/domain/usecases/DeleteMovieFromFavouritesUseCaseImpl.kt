package com.example.movies.domain.usecases

import com.example.kotlinhelpers.Response
import com.example.movies.domain.repositories.MoviesRepository

class DeleteMovieFromFavouritesUseCaseImpl(private val moviesRepository: MoviesRepository) :
    DeleteMovieFromFavouritesUseCase {
    override suspend fun invoke(id: Int): Response<Unit> =
        moviesRepository.deleteMovieFromFavourites(id)
}