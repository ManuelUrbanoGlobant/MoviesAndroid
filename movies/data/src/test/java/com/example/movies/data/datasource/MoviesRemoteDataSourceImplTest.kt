package com.example.movies.data.datasource

import com.example.movies.data.api.MoviesService
import com.example.movies.data.entities.MovieDetailDto
import com.example.movies.data.entities.MovieListDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert

import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MoviesRemoteDataSourceImplTest {

    private lateinit var moviesRemoteDataSourceImpl: MoviesRemoteDataSourceImpl
    private val mockMoviesServices: MoviesService = mockk(relaxed = true)
    private val apiKey = "test key"
    private val mockMovieListDto :MovieListDto = mockk(relaxed = true)
    private val mockMovieDetailDto :MovieDetailDto = mockk(relaxed = true)

    @Before
    fun setUp() {
        moviesRemoteDataSourceImpl = MoviesRemoteDataSourceImpl(mockMoviesServices, apiKey)
    }

    @Test
    fun shouldCallGetListMoviesService() = runBlocking {
        val page = 1
        val responseExpected = Response.success(mockMovieListDto)
        coEvery { mockMoviesServices.getListMovies(apiKey, page) } returns responseExpected

        val response = moviesRemoteDataSourceImpl.getListMovies(page)

        coVerify (exactly = 1) { mockMoviesServices.getListMovies(apiKey, page) }
        Assert.assertEquals(responseExpected, response)
    }

    @Test
    fun shouldCallGetDetailMovieService() = runBlocking {
        val id = 1
        val responseExpected = Response.success(mockMovieDetailDto)
        coEvery { mockMoviesServices.getMovieDetail(id, apiKey) } returns responseExpected

        val response = moviesRemoteDataSourceImpl.getDetailMovie(id)

        coVerify (exactly = 1) { mockMoviesServices.getMovieDetail(id, apiKey) }
        Assert.assertEquals(responseExpected, response)
    }
}