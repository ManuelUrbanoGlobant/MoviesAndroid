package com.example.movies.domain.usecases

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieDetail

interface GetDetailMovieUseCase {
    suspend fun invoke(id: Int): Response<MovieDetail>
}