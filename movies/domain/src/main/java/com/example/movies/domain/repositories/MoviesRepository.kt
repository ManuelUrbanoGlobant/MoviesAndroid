package com.example.movies.domain.repositories

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.entities.MovieDetail

interface MoviesRepository {
    fun getListMovies(): Response<List<Movie>>
    fun getDetailMovie(id: Int): Response<MovieDetail>
}