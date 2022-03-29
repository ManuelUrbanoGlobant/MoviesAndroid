package com.example.movies.data.api

import com.example.movies.data.entities.MovieDetailDto
import com.example.movies.data.entities.MovieListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/popular")
    suspend fun getListMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<MovieListDto>

    @GET("movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int, @Query("api_key") api_key: String): Response<MovieDetailDto>
}