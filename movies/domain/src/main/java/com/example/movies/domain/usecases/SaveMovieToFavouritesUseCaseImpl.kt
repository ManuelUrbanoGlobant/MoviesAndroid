package com.example.movies.domain.usecases

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.repositories.MoviesRepository

class SaveMovieToFavouritesUseCaseImpl(private val moviesRepository: MoviesRepository) :
    SaveMovieToFavouritesUseCase {
    override suspend fun invoke(movie: Movie): Response<Unit> =
        moviesRepository.saveMovieToFavourites(movie)
}