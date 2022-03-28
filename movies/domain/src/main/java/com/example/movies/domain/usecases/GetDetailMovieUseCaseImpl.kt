package com.example.movies.domain.usecases

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.domain.repositories.MoviesRepository

class GetDetailMovieUseCaseImpl (private val moviesRepository: MoviesRepository): GetDetailMovieUseCase {

    override suspend fun invoke(id: Int): Response<MovieDetail> = moviesRepository.getDetailMovie(id)
}