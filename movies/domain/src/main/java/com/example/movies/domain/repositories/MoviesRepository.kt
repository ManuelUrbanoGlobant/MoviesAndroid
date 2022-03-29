package com.example.movies.domain.repositories

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.domain.entities.MovieRecommendationList

interface MoviesRepository {
    suspend fun getListMovies(page: Int?): Response<List<Movie>>
    suspend fun getDetailMovie(id: Int): Response<MovieDetail>
    suspend fun getRecommendedMovies(id: Int, page: Int?): Response<MovieRecommendationList>
}