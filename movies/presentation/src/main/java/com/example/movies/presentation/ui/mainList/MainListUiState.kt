package com.example.movies.presentation.ui.mainList

import com.example.kotlinhelpers.BaseEvent
import com.example.movies.domain.entities.Movie

sealed class MainListUiState : BaseEvent {
    data class GetMainMoviesList(val moviesList: List<Movie>) : MainListUiState()
}