package com.example.movies.domain.entities

data class MovieRecommendation(
    val id: Int,
    val name: String,
    val thumbnail: String
) {
    fun getCompleteThumbnailUrl() = "https://image.tmdb.org/t/p/w300/$thumbnail"
}
