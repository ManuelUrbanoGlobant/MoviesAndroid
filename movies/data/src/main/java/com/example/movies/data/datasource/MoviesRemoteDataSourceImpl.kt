package com.example.movies.data.datasource

import com.example.movies.data.api.MoviesService
import com.example.movies.data.entities.MovieRecommendationListDto
import com.example.movies.data.entities.dto.MovieDetailDTO
import com.example.movies.data.entities.dto.MovieListDTO
import retrofit2.Response

class MoviesRemoteDataSourceImpl(
    private val moviesService: MoviesService,
    private val apiKey: String
) : MoviesRemoteDataSource {
    override suspend fun getListMovies(page: Int): Response<MovieListDTO> =
        moviesService.getListMovies(apiKey, page)

    override suspend fun getDetailMovie(id: Int): Response<MovieDetailDTO> =
        moviesService.getMovieDetail(id, apiKey)

    override suspend fun getRecommendationList(id: Int, page: Int): Response<MovieRecommendationListDto> =
        moviesService.getMovieRecommendations(id, apiKey, page)
}