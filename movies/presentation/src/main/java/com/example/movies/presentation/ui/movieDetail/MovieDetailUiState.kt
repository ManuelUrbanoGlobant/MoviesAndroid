package com.example.movies.presentation.ui.movieDetail

import com.example.movies.domain.entities.MovieDetail
import com.example.movies.domain.entities.MovieRecommendation

sealed class MovieDetailUiState {
    object Init : MovieDetailUiState()
    object Loading : MovieDetailUiState()
    data class GetDetailInformation(val movieDetail: MovieDetail) : MovieDetailUiState()
    data class GetMovieRecommendations(val recommendations: List<MovieRecommendation>) : MovieDetailUiState()
    data class Error(val message: String) : MovieDetailUiState()
}