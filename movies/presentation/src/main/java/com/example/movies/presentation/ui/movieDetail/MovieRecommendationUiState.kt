package com.example.movies.presentation.ui.movieDetail

import com.example.movies.domain.entities.MovieRecommendation

sealed class MovieRecommendationUiState {
    object Init : MovieRecommendationUiState()
    object Loading : MovieRecommendationUiState()
    data class GetMovieRecommendations(val recommendations: List<MovieRecommendation>) : MovieRecommendationUiState()
    data class Error(val message: String) : MovieRecommendationUiState()
}