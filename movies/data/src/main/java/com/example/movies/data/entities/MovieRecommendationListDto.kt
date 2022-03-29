package com.example.movies.data.entities

import com.google.gson.annotations.SerializedName

data class MovieRecommendationListDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieRecommendationDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)