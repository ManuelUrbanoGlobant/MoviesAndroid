package com.example.movies.data.entities.orm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieORM(
    @PrimaryKey
    val id: Int,
    val backdropPath: String,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)