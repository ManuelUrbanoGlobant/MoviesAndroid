package com.example.movies.presentation.ui.moviesList

import com.example.movies.domain.entities.Movie

sealed class MoviesListUiState {
    object Init : MoviesListUiState()
    object Loading : MoviesListUiState()
    data class GetMoviesList(val moviesList: List<Movie>) : MoviesListUiState()
    data class Error(val message: String) : MoviesListUiState()
}
