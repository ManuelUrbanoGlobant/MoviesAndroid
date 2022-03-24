package com.example.movies.data.datasource

import com.example.movies.data.entities.MovieDetailDto
import com.example.movies.data.entities.MovieListDto
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getListMovies(): Response<MovieListDto>
    suspend fun getDetailMovie(id: Int): Response<MovieDetailDto>
}