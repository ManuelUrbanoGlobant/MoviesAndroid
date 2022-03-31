package com.example.movies.presentation.ui.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinhelpers.BaseEvent
import com.example.kotlinhelpers.Response
import com.example.movies.domain.usecases.movieDetail.GetDetailMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailMovieUseCase: GetDetailMovieUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<BaseEvent> =
        MutableStateFlow(BaseEvent.Init)

    val uiState: StateFlow<BaseEvent> = _uiState

    fun getDetailMovie(id: Int) {
        viewModelScope.launch(dispatcher) {
            _uiState.emit(BaseEvent.Loading)

            when (val response = movieDetailMovieUseCase.invoke(id)) {
                is Response.Success -> {
                    _uiState.emit(MovieDetailUiState.GetDetailInformation(response.value))
                }
                is Response.Error -> _uiState.emit(BaseEvent.Error(response.message))
            }
        }
    }
}