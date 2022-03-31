package com.example.movies.domain.entities

data class Movie(
    val id: Int = 0,
    val name: String = "",
    val date: String = "",
    val overview: String = "",
    val score: Double = 0.0,
    val thumbnail: String? = ""
) {
    fun getCompleteUrlToDetails() = "https://image.tmdb.org/t/p/original$thumbnail"
}
