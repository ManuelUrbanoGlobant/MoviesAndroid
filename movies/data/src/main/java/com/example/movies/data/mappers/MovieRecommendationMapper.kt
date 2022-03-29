package com.example.movies.data.mappers

import com.example.movies.data.entities.MovieRecommendationDto
import com.example.movies.data.entities.MovieRecommendationListDto
import com.example.movies.domain.entities.MovieRecommendation
import com.example.movies.domain.entities.MovieRecommendationList

fun MovieRecommendationListDto.toDomain() = MovieRecommendationList(
    page = page,
    totalPages = totalPages,
    movies = movies.map { it.toDomain() },
    totalResults = totalResults
)

fun MovieRecommendationDto.toDomain() = MovieRecommendation(
    id = id,
    name = title,
    thumbnail = posterPath
)