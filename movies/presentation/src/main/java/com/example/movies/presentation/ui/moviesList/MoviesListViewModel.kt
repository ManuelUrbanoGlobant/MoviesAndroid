package com.example.movies.presentation.ui.moviesList

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.usecases.moviesList.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val moviesListUseCase: GetMoviesListUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<MoviesListUiState> =
        MutableStateFlow(MoviesListUiState.Init)

    val uiState: StateFlow<MoviesListUiState> = _uiState
    private val movieList = mutableStateListOf<Movie>()
    private var nextPage = 1
    var maxPage = 3

    init {
        getMoviesList()
    }

    fun getMoviesList() {
        viewModelScope.launch(dispatcher) {
            if (nextPage == 1) _uiState.emit(MoviesListUiState.Loading)
            delay(500)
            movieList.remove(Movie())
            when (val response = moviesListUseCase.invoke(nextPage)) {
                is Response.Success -> {
                    maxPage = response.value.totalPages
                    movieList.addAll(response.value.movies)
                    movieList.add(Movie())
                    _uiState.emit(MoviesListUiState.GetMoviesList(movieList))
                }
                is Response.Error -> _uiState.emit(MoviesListUiState.Error(response.message))
            }
            nextPage++
        }
    }

}