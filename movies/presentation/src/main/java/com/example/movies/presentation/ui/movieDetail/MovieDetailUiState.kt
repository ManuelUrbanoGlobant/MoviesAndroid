package com.example.movies.presentation.ui.movieDetail

import com.example.kotlinhelpers.BaseEvent
import com.example.movies.domain.entities.MovieDetail

sealed class MovieDetailUiState : BaseEvent {

    data class GetDetailInformation(val movieDetail: MovieDetail) : MovieDetailUiState()
}