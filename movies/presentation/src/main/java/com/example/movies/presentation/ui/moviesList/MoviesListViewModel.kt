package com.example.movies.presentation.ui.moviesList

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.usecases.favorites.DeleteMovieFromFavouritesUseCase
import com.example.movies.domain.usecases.favorites.GetFavouritesMoviesUseCase
import com.example.movies.domain.usecases.favorites.SaveMovieToFavouritesUseCase
import com.example.movies.domain.usecases.moviesList.GetMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val moviesListUseCase: GetMoviesListUseCase,
    private val getFavoriteUseCae: GetFavouritesMoviesUseCase,
    private val saveMovieToFavouritesUseCase: SaveMovieToFavouritesUseCase,
    private val deleteMovieFromFavouritesUseCase: DeleteMovieFromFavouritesUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState: MutableStateFlow<MoviesListUiState> =
        MutableStateFlow(MoviesListUiState.Init)

    val uiState: StateFlow<MoviesListUiState> = _uiState
    private val movieList = mutableStateListOf<Movie>()
    private var favoritesMovies: List<Movie> = emptyList()
    private var nextPage = 1
    private var maxPage = 3

    init {
        getFavorites()
    }

    fun getMoviesList() {
        viewModelScope.launch(dispatcher) {
            if (nextPage == 1) _uiState.emit(MoviesListUiState.Loading)

            movieList.removeIf { it.id == 0 }
            when (val response = moviesListUseCase.invoke(nextPage)) {
                is Response.Success -> {
                    maxPage = response.value.totalPages
                    val movies = response.value.movies.map { movie ->
                        movie.isFavorite = favoritesMovies.firstOrNull { it.id == movie.id } != null
                        return@map movie
                    }
                    movieList.addAll(movies)
                    movieList.add(Movie())
                    _uiState.emit(MoviesListUiState.GetMoviesList(movieList))
                }
                is Response.Error -> _uiState.emit(MoviesListUiState.Error(response.message))
            }
            nextPage++
        }
    }

    private fun getFavorites() {
        viewModelScope.launch(dispatcher) {
            when (val response = getFavoriteUseCae.invoke()) {
                is Response.Success -> {
                    response.value.collect {
                        favoritesMovies = it
                        getMoviesList()
                    }
                }
                is Response.Error -> _uiState.emit(MoviesListUiState.Error(response.message))
            }
            _uiState.emit(MoviesListUiState.GetMoviesList(movieList))
        }
    }


    fun changeStateFavorite(isFavorite: Boolean, movie: Movie) {
        viewModelScope.launch(dispatcher) {
            movie.isFavorite = isFavorite
            if (isFavorite) saveMovieToFavouritesUseCase.invoke(movie = movie)
            else deleteMovieFromFavouritesUseCase.invoke(movie.id)
        }
    }


}