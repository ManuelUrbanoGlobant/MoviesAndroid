package com.example.movies.data.datasource

import com.example.movies.data.api.MoviesService
import com.example.movies.data.entities.MovieDetailDto
import com.example.movies.data.entities.MovieListDto
import retrofit2.Response

class MoviesRemoteDataSourceImpl(
    private val moviesService: MoviesService,
    private val apiKey: String
) : MoviesRemoteDataSource {
    override suspend fun getListMovies(page: Int): Response<MovieListDto> =
        moviesService.getListMovies(apiKey, page)

    override suspend fun getDetailMovie(id: Int): Response<MovieDetailDto> =
        moviesService.getMovieDetail(id, apiKey)
}