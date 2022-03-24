package com.example.movies.presentation.ui.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.entities.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<MovieDetailUiState> =
        MutableStateFlow(MovieDetailUiState.Init)

    val uiState: StateFlow<MovieDetailUiState> = _uiState

    fun getDetailMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(MovieDetailUiState.Loading)
            delay(1500)
            val detail = MovieDetail(
                name = "Sonic the Hedgehog 2",
                date = "2022-03-30",
                time = 122,
                overview = "After settling in Green Hills, Sonic is eager to prove he has what it takes to be a true hero. His test comes when Dr. Robotnik returns, this time with a new partner, Knuckles, in search for an emerald that has the power to destroy civilizations. Sonic teams up with his own sidekick, Tails, and together they embark on a globe-trotting journey to find the emerald before it falls into the wrong hands",
                score = 0.0f,
                thumbnail = "/lrP1TQf3stZveNEyviUUcSh8HLA.jpg"
            )

            _uiState.emit(MovieDetailUiState.GetDetailInformation(detail))
        }
    }
}