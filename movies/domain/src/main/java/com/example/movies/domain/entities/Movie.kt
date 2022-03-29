package com.example.movies.domain.entities

data class Movie(
    val id: Int,
    val name: String,
    val date: String,
    val overview: String,
    val score: Double,
    val thumbnail: String
)
