package com.example.movies.data.datasource

import com.example.movies.data.entities.MovieDetailDto
import com.example.movies.data.entities.MovieListDto
import com.example.movies.data.entities.MovieRecommendationListDto
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getListMovies(page: Int): Response<MovieListDto>
    suspend fun getDetailMovie(id: Int): Response<MovieDetailDto>
    suspend fun getRecommendationList(id: Int, page: Int): Response<MovieRecommendationListDto>
}