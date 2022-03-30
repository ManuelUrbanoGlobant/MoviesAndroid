package com.example.movies.presentation.ui.mainList

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinhelpers.BaseEvent
import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.usecases.moviesList.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainListViewModel @Inject constructor(
    private val moviesListUseCase: GetMoviesListUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<BaseEvent> =
        MutableStateFlow(BaseEvent.Init)

    val uiState: StateFlow<BaseEvent> = _uiState
    private val movieList = mutableStateListOf<Movie>()

    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        viewModelScope.launch(dispatcher) {
            _uiState.emit(BaseEvent.Loading)
            when (val response = moviesListUseCase.invoke(1)) {
                is Response.Success -> {
                    movieList.addAll(response.value.movies)
                    _uiState.emit(MainListUiState.GetMainMoviesList(movieList))
                }
                is Response.Error -> _uiState.emit(BaseEvent.Error(response.message))
            }
        }
    }

}