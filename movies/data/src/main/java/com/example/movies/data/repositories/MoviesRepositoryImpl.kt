package com.example.movies.data.repositories

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.domain.repositories.MoviesRepository

class MoviesRepositoryImpl : MoviesRepository {
    override fun getListMovies(): Response<List<Movie>> {
        TODO("Not yet implemented")
    }

    override fun getDetailMovie(id: Int): Response<MovieDetail> {
        TODO("Not yet implemented")
    }
}