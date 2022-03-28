package com.example.movies.presentation.ui.moviesList

import androidx.compose.runtime.mutableStateListOf
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
    val movieList = mutableStateListOf<Movie>()
    var nextPage = 1
    var maxPage = 3

    init {
        getMoviesList()
    }

    fun getMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (nextPage <= maxPage) {
                _uiState.emit(MoviesListUiState.Loading)
                delay(1500)
                when (nextPage) {
                    1 -> {
                        movieList.add(
                            Movie(
                                1, "Spider-Man: No Way Home",
                                "2021-12-15",
                                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                                "steven",
                                8.4,
                                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                2, "Turning Red",
                                "2022-03-10",
                                "Thirteen-year-old Mei is experiencing the awkwardness of being a teenager with a twist – when she gets too excited, she transforms into a giant red panda.",
                                "steven",
                                7.2,
                                "/qsdjk9oAKSQMWs0Vt5Pyfh6O4GZ.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                3, "The Adam Project",
                                "2022-03-10",
                                "After accidentally crash-landing in 2022, time-traveling fighter pilot Adam Reed teams up with his 12-year-old self on a mission to save the future.",
                                "steven",
                                7.5,
                                "/wFjboE0aFZNbVOF05fzrka9Fqyx.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                4, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                5, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                6, "Spider-Man: No Way Home",
                                "2021-12-15",
                                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                                "steven",
                                8.4,
                                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                7, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                8, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                9, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                10, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                    }
                    2 -> {
                        movieList.add(
                            Movie(
                                11, "Spider-Man: No Way Home",
                                "2021-12-15",
                                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                                "steven",
                                8.4,
                                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                12, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                13, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                14, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                15, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                16, "Spider-Man: No Way Home",
                                "2021-12-15",
                                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                                "steven",
                                8.4,
                                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                17, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                18, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                19, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                20, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                    }
                    3 -> {

                        movieList.add(
                            Movie(
                                21, "Spider-Man: No Way Home",
                                "2021-12-15",
                                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                                "steven",
                                8.4,
                                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                22, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                23, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                24, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                25, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                26, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                27, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                28, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                29, "Blacklight",
                                "2022-03-10",
                                "Travis Block is a shadowy Government agent who specializes in removing operatives whose covers have been exposed. He then has to uncover a deadly conspiracy within his own ranks that reaches the highest echelons of power.",
                                "steven",
                                5.2,
                                "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg"
                            )
                        )
                        movieList.add(
                            Movie(
                                30, "Turning Red",
                                "2022-03-10",
                                "Thirteen-year-old Mei is experiencing the awkwardness of being a teenager with a twist – when she gets too excited, she transforms into a giant red panda.",
                                "steven",
                                7.2,
                                "/qsdjk9oAKSQMWs0Vt5Pyfh6O4GZ.jpg"
                            )
                        )
                    }
                }

                if (movieList.isEmpty()) {
                    _uiState.emit(MoviesListUiState.Error("No se encontraron peliculas"))
                } else {
                    _uiState.emit(MoviesListUiState.GetMoviesList(movieList))
                }

                nextPage++
            }
        }
    }

}