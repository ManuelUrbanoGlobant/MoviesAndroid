package com.example.movies.data.repositories

import com.example.kotlinhelpers.Response
import com.example.movies.data.datasource.MoviesLocalDataSource
import com.example.movies.data.datasource.MoviesRemoteDataSource
import com.example.movies.data.entities.dto.MovieDetailDTO
import com.example.movies.data.entities.dto.MovieListDTO
import com.example.movies.data.entities.orm.MovieORM
import com.example.movies.data.mappers.MovieDTOMapper
import com.example.movies.data.mappers.MovieDetailDTOMapper
import com.example.movies.data.mappers.MovieORMMapper
import com.example.movies.domain.entities.Movie
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import retrofit2.Response as ResponseRetrofit

class MoviesRepositoryImplTest {

    private lateinit var moviesRepositoryImpl: MoviesRepositoryImpl
    private val mockMoviesRemoteDataSource: MoviesRemoteDataSource = mockk()
    private val mockMoviesLocalDataSource: MoviesLocalDataSource = mockk()
    private val mockMovieMapper: MovieDTOMapper = mockk(relaxed = true)
    private val mockMovieDetailDTOMapper: MovieDetailDTOMapper = mockk(relaxed = true)
    private val mockMovieORMMapper: MovieORMMapper = mockk(relaxed = true)
    private val mockMovieListDto: MovieListDTO = mockk(relaxed = true)
    private val mockMovieDetailDto: MovieDetailDTO = mockk(relaxed = true)
    private val mockMovieORM: MovieORM = mockk(relaxed = true)
    private val movieId = 1
    private val pageNumber = 1

    @Before
    fun setUp() {
        moviesRepositoryImpl =
            MoviesRepositoryImpl(
                mockMoviesRemoteDataSource,
                mockMoviesLocalDataSource,
                mockMovieMapper,
                mockMovieORMMapper,
                mockMovieDetailDTOMapper
            )
    }

    @Test
    fun shouldCallGetListMoviesDataSource() = runBlocking {
        coEvery { mockMoviesRemoteDataSource.getListMovies(pageNumber) } returns ResponseRetrofit.success(
            mockMovieListDto
        )

        moviesRepositoryImpl.getListMovies(pageNumber)

        coVerify(exactly = 1) { mockMoviesRemoteDataSource.getListMovies(pageNumber) }
    }

    @Test
    fun shouldResponseSuccessCallGetListMoviesDataSource() = runBlocking {
        val responseRetrofit = ResponseRetrofit.success(mockMovieListDto)
        coEvery { mockMoviesRemoteDataSource.getListMovies(pageNumber) } returns responseRetrofit

        val response = moviesRepositoryImpl.getListMovies(pageNumber)

        assertTrue(response is Response.Success)
    }

    @Test
    fun shouldResponseErrorCallGetListMoviesDataSource() = runBlocking {
        val responseRetrofit = ResponseRetrofit.error<MovieListDTO>(404, mockk(relaxed = true))
        coEvery { mockMoviesRemoteDataSource.getListMovies(pageNumber) } returns responseRetrofit

        val response = moviesRepositoryImpl.getListMovies(pageNumber)

        assertTrue(response is Response.Error)
    }

    @Test
    fun shouldCatchCallGetListMoviesDataSource() = runBlocking {
        val messageException = "Invalid Test Exception"
        coEvery { mockMoviesRemoteDataSource.getListMovies(pageNumber) } throws Exception(
            messageException
        )

        val response = moviesRepositoryImpl.getListMovies(pageNumber)
        assertEquals((response as Response.Error).message, messageException)
    }


    @Test
    fun shouldCallGetDetailMovieDataSource() = runBlocking {
        coEvery { mockMoviesRemoteDataSource.getDetailMovie(movieId) } returns ResponseRetrofit.success(
            mockMovieDetailDto
        )

        moviesRepositoryImpl.getDetailMovie(movieId)

        coVerify(exactly = 1) { mockMoviesRemoteDataSource.getDetailMovie(movieId) }
    }

    @Test
    fun shouldResponseSuccessGetDetailMovie() = runBlocking {
        val responseRetrofit = ResponseRetrofit.success(mockMovieDetailDto)
        coEvery { mockMoviesRemoteDataSource.getDetailMovie(movieId) } returns responseRetrofit

        val response = moviesRepositoryImpl.getDetailMovie(movieId)

        assertTrue(response is Response.Success)
    }

    @Test
    fun shouldResponseErrorGetDetailMovie() = runBlocking {
        val responseRetrofit = ResponseRetrofit.error<MovieDetailDTO>(404, mockk(relaxed = true))
        coEvery { mockMoviesRemoteDataSource.getDetailMovie(movieId) } returns responseRetrofit

        val response = moviesRepositoryImpl.getDetailMovie(movieId)

        assertTrue(response is Response.Error)
    }

    @Test
    fun shouldCatchGetDetailMovie() = runBlocking {
        val messageException = "Invalid Test Exception"
        coEvery { mockMoviesRemoteDataSource.getDetailMovie(movieId) } throws Exception(
            messageException
        )

        val response = moviesRepositoryImpl.getDetailMovie(movieId)
        assertEquals((response as Response.Error).message, messageException)
    }

}