package com.example.movies.domain.entities

data class MovieList(
    val page: Int = 1,
    val movies: List<Movie> = listOf(),
    val totalPages: Int = 1,
    val totalResults: Int = 0
) {
}