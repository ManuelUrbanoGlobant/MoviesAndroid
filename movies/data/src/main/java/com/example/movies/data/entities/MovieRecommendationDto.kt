package com.example.movies.data.entities

import com.google.gson.annotations.SerializedName

data class MovieRecommendationDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String
)