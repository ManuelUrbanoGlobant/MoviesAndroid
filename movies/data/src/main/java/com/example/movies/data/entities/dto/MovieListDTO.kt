package com.example.movies.data.entities.dto

import com.google.gson.annotations.SerializedName

data class MovieListDTO(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)