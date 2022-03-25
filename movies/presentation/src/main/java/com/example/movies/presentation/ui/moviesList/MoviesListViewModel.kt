package com.example.movies.presentation.ui.moviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.entities.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<MoviesListUiState> =
        MutableStateFlow(MoviesListUiState.Init)

    val uiState: StateFlow<MoviesListUiState> = _uiState

    fun getMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.emit(MoviesListUiState.Loading)
            delay(1500)

            val movieList = mutableListOf<Movie>()
            movieList.add(Movie("Spider-Man: No Way Home",
                "2021-12-15",
             "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "steven",
                8,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"))
            movieList.add(Movie("Turning Red",
                "2022-03-10",
                "Thirteen-year-old Mei is experiencing the awkwardness of being a teenager with a twist – when she gets too excited, she transforms into a giant red panda.",
                "steven",
                7,
                "/qsdjk9oAKSQMWs0Vt5Pyfh6O4GZ.jpg"))
            movieList.add(Movie("The Adam Project",
                "2022-03-10",
                "After accidentally crash-landing in 2022, time-traveling fighter pilot Adam Reed teams up with his 12-year-old self on a mission to save the future.",
                "steven",
                7,
                "/wFjboE0aFZNbVOF05fzrka9Fqyx.jpg"))
            movieList.add(Movie("Blacklight",
                "2022-03-10",
                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                "steven",
                5,
                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"))

            _uiState.emit(MoviesListUiState.GetMoviesList(movieList))
        }
    }

}