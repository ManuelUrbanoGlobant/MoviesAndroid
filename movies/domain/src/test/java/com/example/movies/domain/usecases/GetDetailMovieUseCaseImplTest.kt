package com.example.movies.domain.usecases

import com.example.kotlinhelpers.Response
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.domain.repositories.MoviesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions

import org.junit.jupiter.api.Test

class GetDetailMovieUseCaseImplTest {

    private val mockMoviesRepository: MoviesRepository = mockk(relaxed = true)
    private val mockMovieDetail: MovieDetail = mockk(relaxed = true)
    private lateinit var getDetailMovieUseCaseImpl: GetDetailMovieUseCaseImpl

    @Test
    fun shouldInvokeRepository() = runBlocking {
        getDetailMovieUseCaseImpl = GetDetailMovieUseCaseImpl(mockMoviesRepository)
        val movieId = 1
        coEvery { mockMoviesRepository.getDetailMovie(movieId) } returns Response.Success(
            mockMovieDetail
        )
        val response = getDetailMovieUseCaseImpl.invoke(movieId)

        Assertions.assertTrue(response is Response.Success)

        coVerify { mockMoviesRepository.getDetailMovie(movieId) }
    }
}