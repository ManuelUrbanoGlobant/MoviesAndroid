package com.example.movies.presentation.ui.moviesList

import com.example.kotlinhelpers.BaseEvent
import com.example.movies.domain.entities.Movie

sealed class MoviesListUiState : BaseEvent {
    data class GetMoviesList(val moviesList: List<Movie>) : MoviesListUiState()
}
