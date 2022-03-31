package com.example.movies.presentation.ui.moviedetail

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.domain.usecases.movieDetail.GetDetailMovieUseCase
import com.example.movies.presentation.MainCoroutineRule
import com.example.movies.presentation.ui.movieDetail.MovieDetailUiState
import com.example.movies.presentation.ui.movieDetail.MovieDetailViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private val movieId = 1
    private val getMovieDetailMovieUseCase: GetDetailMovieUseCase = mockk()
    private val movieDetail: MovieDetail = mockk(relaxed = true)

    @Before
    fun setup() {

        movieDetailViewModel =
            MovieDetailViewModel(getMovieDetailMovieUseCase, mainCoroutineRule.testDispatcher)
        every { movieDetail.name } returns "name"
        every { movieDetail.score } returns 10.0
    }

    @Test
    fun shouldCallGetDetailMovieUseCase() {
        coEvery { getMovieDetailMovieUseCase.invoke(movieId) } returns Response.Success(movieDetail)

        movieDetailViewModel.getDetailMovie(movieId)

        coVerify { getMovieDetailMovieUseCase.invoke(movieId) }
    }

    @Test
    fun shouldGetDetailSuccessResponse() {
        coEvery { getMovieDetailMovieUseCase.invoke(movieId) } returns Response.Success(movieDetail)
        assertTrue(movieDetailViewModel.uiState.value is MovieDetailUiState.Init)

        movieDetailViewModel.getDetailMovie(movieId)

        assertTrue(movieDetailViewModel.uiState.value is MovieDetailUiState.GetDetailInformation)
    }

    @Test
    fun shouldGetDetailErrorResponse() {
        coEvery { getMovieDetailMovieUseCase.invoke(movieId) } returns Response.Error("Error")
        assertTrue(movieDetailViewModel.uiState.value is MovieDetailUiState.Init)

        movieDetailViewModel.getDetailMovie(movieId)

        assertTrue(movieDetailViewModel.uiState.value is MovieDetailUiState.Error)
    }
}