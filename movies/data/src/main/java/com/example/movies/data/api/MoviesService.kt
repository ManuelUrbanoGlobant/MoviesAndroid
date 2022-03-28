package com.example.movies.data.api

import com.example.movies.data.entities.dto.MovieDetailDTO
import com.example.movies.data.entities.dto.MovieListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("/movie/popular")
    suspend fun getListMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<MovieListDTO>

    @GET("/movie/{id}")
    suspend fun getMovieDetail(@Path("id") id: Int): Response<MovieDetailDTO>
}