package com.example.movies.presentation.ui.moviedetail

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.domain.entities.MovieRecommendationList
import com.example.movies.domain.usecases.movieRecommendations.GetMovieRecommendationsUseCase
import com.example.movies.domain.usecases.movieDetail.GetDetailMovieUseCase
import com.example.movies.presentation.MainCoroutineRule
import com.example.movies.presentation.ui.movieDetail.MovieDetailUiState
import com.example.movies.presentation.ui.movieDetail.MovieDetailViewModel
import com.example.movies.presentation.ui.movieDetail.MovieRecommendationUiState
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
    private val getMovieRecommendationsUseCase: GetMovieRecommendationsUseCase = mockk()
    private val movieDetail: MovieDetail = mockk(relaxed = true)
    private val movieRecommendationList: MovieRecommendationList = mockk(relaxed = true)

    @Before
    fun setup() {
        movieDetailViewModel =
            MovieDetailViewModel(
                getMovieDetailMovieUseCase,
                getMovieRecommendationsUseCase,
                mainCoroutineRule.testDispatcher
            )
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
        assertTrue(movieDetailViewModel.movieDetailUiState.value is MovieDetailUiState.Init)

        movieDetailViewModel.getDetailMovie(movieId)

        assertTrue(movieDetailViewModel.movieDetailUiState.value is MovieDetailUiState.GetDetailInformation)
    }

    @Test
    fun shouldGetDetailErrorResponse() {
        coEvery { getMovieDetailMovieUseCase.invoke(movieId) } returns Response.Error("Error")
        assertTrue(movieDetailViewModel.movieDetailUiState.value is MovieDetailUiState.Init)

        movieDetailViewModel.getDetailMovie(movieId)

        assertTrue(movieDetailViewModel.movieDetailUiState.value is MovieDetailUiState.Error)
    }

    @Test
    fun shouldCallGetMovieRecommendationsUseCase() {
        coEvery { getMovieRecommendationsUseCase.invoke(movieId) } returns Response.Success(movieRecommendationList)

        movieDetailViewModel.getMovieRecommendations(movieId)

        coVerify { getMovieRecommendationsUseCase.invoke(movieId) }
    }

    @Test
    fun shouldGetMovieRecommendationsSuccessResponse() {
        coEvery { getMovieRecommendationsUseCase.invoke(movieId) } returns Response.Success(movieRecommendationList)
        assertTrue(movieDetailViewModel.movieRecommendationUiState.value is MovieRecommendationUiState.Init)

        movieDetailViewModel.getMovieRecommendations(movieId)

        assertTrue(movieDetailViewModel.movieRecommendationUiState.value is MovieRecommendationUiState.GetMovieRecommendations)
    }

    @Test
    fun shouldGetRecommendationErrorResponse() {
        coEvery { getMovieRecommendationsUseCase.invoke(movieId) } returns Response.Error("Error")
        assertTrue(movieDetailViewModel.movieRecommendationUiState.value is MovieRecommendationUiState.Init)

        movieDetailViewModel.getMovieRecommendations(movieId)

        assertTrue(movieDetailViewModel.movieRecommendationUiState.value is MovieRecommendationUiState.Error)
    }
}