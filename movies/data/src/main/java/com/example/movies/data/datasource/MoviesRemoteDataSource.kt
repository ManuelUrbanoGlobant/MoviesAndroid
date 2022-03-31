package com.example.movies.data.datasource

import com.example.movies.data.entities.dto.MovieDetailDTO
import com.example.movies.data.entities.dto.MovieListDTO
import retrofit2.Response

interface MoviesRemoteDataSource {

    suspend fun getListMovies(page: Int): Response<MovieListDTO>

    suspend fun getDetailMovie(id: Int): Response<MovieDetailDTO>
}