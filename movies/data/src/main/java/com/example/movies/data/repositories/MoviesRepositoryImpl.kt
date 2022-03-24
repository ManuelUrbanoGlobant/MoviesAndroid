package com.example.movies.data.repositories

import com.example.kotlinhelpers.Response
import com.example.movies.data.datasource.MoviesRemoteDataSource
import com.example.movies.data.mappers.MovieDetailMapper
import com.example.movies.data.mappers.MovieMapper
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.domain.repositories.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val movieMapper: MovieMapper,
    private val movieDetailMapper: MovieDetailMapper
) : MoviesRepository {
    override suspend fun getListMovies(): Response<List<Movie>> {
        lateinit var movieList: List<Movie>
        try {
            val response = moviesRemoteDataSource.getListMovies()
            val body = response.body()
            if (body != null && response.isSuccessful) {
                movieList = movieMapper.fromEntityList(body.movies)
            } else {
                return Response.Error("something went wrong")
            }
        } catch (error: Exception) {
            return Response.Error(error.message.toString())
        }
        return Response.Success(movieList)
    }

    override suspend fun getDetailMovie(id: Int): Response<MovieDetail> {
        lateinit var movieDetail: MovieDetail
        try {
            val response = moviesRemoteDataSource.getDetailMovie(id)
            val body = response.body()
            if (body != null && response.isSuccessful) {
                movieDetail = movieDetailMapper.mapFromEntity(body)
            } else {
                return Response.Error("something went wrong")
            }
        } catch (error: Exception) {
            return Response.Error(error.message.toString())
        }
        return Response.Success(movieDetail)
    }
}