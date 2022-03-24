package com.example.movies.data.api

import com.example.movies.domain.entities.Movie
import retrofit2.Response
import retrofit2.http.GET

interface MoviesService {

    @GET("")
    suspend fun getListMovies() : Response<List<Movie>>

}