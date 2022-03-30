package com.example.movies.domain.repositories

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.domain.entities.MovieList

interface MoviesRepository {
    suspend fun getListMovies(page: Int?): Response<MovieList>
    suspend fun getDetailMovie(id: Int): Response<MovieDetail>
}