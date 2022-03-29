package com.example.movies.domain.entities

data class MovieDetail(
    val id: Int,
    val name: String,
    val date: String,
    val overview: String,
    val time: Int,
    val score: Double,
    val thumbnail: String
) {
    fun getCompleteUrlToDetails() = "https://image.tmdb.org/t/p/original$thumbnail"
}
