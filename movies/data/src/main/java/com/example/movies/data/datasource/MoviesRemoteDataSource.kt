package com.example.movies.data.datasource

interface MoviesRemoteDataSource {
    fun getListMovies()
    fun getDetailMovie(id: Int)
}