package com.example.movies.domain.entities

data class Movie(
    val name: String,
    val date: String,
    val overview: String,
    val director: String,
    val score: Int,
    val thumbnail: String
)
