package com.example.movies.presentation.ui.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinhelpers.Response
import com.example.movies.domain.usecases.GetDetailMovieUseCase
import com.example.movies.domain.usecases.GetDetailMovieUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailMovieUseCase: GetDetailMovieUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(MovieDetailUiState.Init)

    val uiState: StateFlow<MovieDetailUiState> = _uiState

    fun getDetailMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(MovieDetailUiState.Loading)
            when(val response = movieDetailMovieUseCase.invoke(GetDetailMovieUseCaseImpl.Params(id))) {
                is Response.Success -> _uiState.emit(MovieDetailUiState.GetDetailInformation(response.value))
                is Response.Error -> _uiState.emit(MovieDetailUiState.Error(response.message))
            }
        }
    }
}