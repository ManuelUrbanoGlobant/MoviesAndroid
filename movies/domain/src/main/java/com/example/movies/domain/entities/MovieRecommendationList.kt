package com.example.movies.domain.entities

data class MovieRecommendationList(
    val page: Int = 1,
    val movies: List<MovieRecommendation> = listOf(),
    val totalPages: Int = 1,
    val totalResults: Int = 0
)
