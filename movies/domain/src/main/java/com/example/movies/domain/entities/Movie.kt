package com.example.movies.domain.entities

data class Movie(
    val id: Int,
    val name: String,
    val date: String,
    val overview: String,
    val director: String,
    val score: Double,
    val thumbnail: String
) {
    fun getCompleteUrlToDetails() = "https://image.tmdb.org/t/p/original$thumbnail"
}
