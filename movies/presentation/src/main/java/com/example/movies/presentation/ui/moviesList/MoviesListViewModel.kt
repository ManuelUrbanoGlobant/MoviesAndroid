package com.example.movies.presentation.ui.moviesList

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.usecases.moviesList.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(private val moviesListUseCase : GetMoviesListUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<MoviesListUiState> =
        MutableStateFlow(MoviesListUiState.Init)

    val uiState: StateFlow<MoviesListUiState> = _uiState
    private  val movieList = mutableStateListOf<Movie>()
    var nextPage = 1
    var maxPage = 3

    init {
        getMoviesList()
    }

    private fun getMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1500)
            when (val response = moviesListUseCase.invoke(1)) {
                is Response.Success -> {
                    movieList.add(Movie())
                    movieList.addAll(response.value)
                    _uiState.emit(MoviesListUiState.GetMoviesList(movieList))
                    Log.d("PRUEBA",movieList.size.toString())
                }
                is Response.Error -> _uiState.emit(MoviesListUiState.Error(response.message))
            }

                //_uiState.emit(MoviesListUiState.Loading)
               /* delay(1500)

                movieList.add(Movie())
                if (movieList.isEmpty()) {
                    _uiState.emit(MoviesListUiState.Error("No se encontraron peliculas"))
                } else {
                    _uiState.emit(MoviesListUiState.GetMoviesList(movieList))
                }
                */
            nextPage++
        }
    }

    fun removeProgressItem() {
        viewModelScope.launch {
            delay(400)
            movieList.remove(Movie())
            getMoviesList()
        }
    }
}