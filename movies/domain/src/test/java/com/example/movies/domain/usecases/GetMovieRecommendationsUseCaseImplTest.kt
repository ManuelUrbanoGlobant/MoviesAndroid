package com.example.movies.domain.usecases

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieRecommendationList
import com.example.movies.domain.repositories.MoviesRepository
import com.example.movies.domain.usecases.movieRecommendations.GetMovieRecommendationsUseCaseImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GetMovieRecommendationsUseCaseImplTest {

    private val mockMoviesRepository: MoviesRepository = mockk(relaxed = true)
    private val mockMovieDetail: MovieRecommendationList = mockk(relaxed = true)
    private lateinit var getMovieRecommendationsUseCaseImpl: GetMovieRecommendationsUseCaseImpl

    @Test
    fun shouldInvokeRepository() = runBlocking {
        getMovieRecommendationsUseCaseImpl = GetMovieRecommendationsUseCaseImpl(mockMoviesRepository)
        val movieId = 1
        val page = 1
        coEvery { mockMoviesRepository.getRecommendedMovies(movieId, page) } returns Response.Success(
            mockMovieDetail
        )
        val response = getMovieRecommendationsUseCaseImpl.invoke(movieId)

        Assertions.assertTrue(response is Response.Success)

        coVerify { mockMoviesRepository.getRecommendedMovies(movieId, page) }
    }
}