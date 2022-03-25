package com.example.movies.presentation

import com.example.movies.domain.usecases.GetDetailMovieUseCase
import com.example.movies.presentation.ui.movieDetail.MovieDetailViewModel
import io.mockk.mockk
import org.junit.Before
import org.junit.Test


class MovieDetailViewModelTest {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private val movieId = 1
    private val getMovieDetailMovieUseCase: GetDetailMovieUseCase = mockk()

    @Before
    fun setup() {
        movieDetailViewModel = MovieDetailViewModel(getMovieDetailMovieUseCase)
    }

    @Test
    fun errorResponseTest() {
//        coEvery { getMovieDetailMovieUseCase.invoke(GetDetailMovieUseCaseImpl.Params(movieId)) } returns  errorResponse
        movieDetailViewModel.getDetailMovie(movieId)
    }
}