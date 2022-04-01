package com.example.movies.presentation.ui.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinhelpers.Response
import com.example.movies.domain.usecases.movieDetail.GetDetailMovieUseCase
import com.example.movies.domain.usecases.movieRecommendations.GetMovieRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailMovieUseCase: GetDetailMovieUseCase,
    private val movieRecommendationsUseCase: GetMovieRecommendationsUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _movieDetailUiState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(MovieDetailUiState.Init)

    val movieDetailUiState: StateFlow<MovieDetailUiState> = _movieDetailUiState

    private val _movieRecommendationUiState: MutableStateFlow<MovieRecommendationUiState> =
        MutableStateFlow(MovieRecommendationUiState.Init)

    val movieRecommendationUiState: StateFlow<MovieRecommendationUiState> = _movieRecommendationUiState

    fun fetchData(movieId: Int) {
        getDetailMovie(movieId)
        viewModelScope.launch {
            movieDetailUiState.collect {
                if (it is MovieDetailUiState.GetDetailInformation) {
                    getMovieRecommendations(movieId)
                }
            }
        }
    }

    fun getDetailMovie(id: Int) {
        viewModelScope.launch(dispatcher) {
            _movieDetailUiState.emit(MovieDetailUiState.Loading)

            when (val response = movieDetailMovieUseCase.invoke(id)) {
                is Response.Success -> {
                    _movieDetailUiState.emit(MovieDetailUiState.GetDetailInformation(response.value))
                }
                is Response.Error -> _movieDetailUiState.emit(MovieDetailUiState.Error(response.message))
            }
        }
    }

    fun getMovieRecommendations(movieId: Int) {
        viewModelScope.launch(dispatcher) {
            _movieRecommendationUiState.emit(MovieRecommendationUiState.Loading)
            when (val response = movieRecommendationsUseCase.invoke(movieId)) {
                is Response.Success -> {
                    _movieRecommendationUiState.emit(MovieRecommendationUiState.GetMovieRecommendations(response.value.movies))
                }
                is Response.Error -> _movieRecommendationUiState.emit(MovieRecommendationUiState.Error(response.message))
            }
        }
    }
}