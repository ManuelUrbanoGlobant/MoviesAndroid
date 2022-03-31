package com.example.movies.data.repositories

import com.example.kotlinhelpers.Response
import com.example.movies.data.datasource.MoviesLocalDataSource
import com.example.movies.data.datasource.MoviesRemoteDataSource
import com.example.movies.data.mappers.MovieDTOMapper
import com.example.movies.data.mappers.MovieDetailDTOMapper
import com.example.movies.data.mappers.MovieORMMapper
import com.example.movies.data.mappers.MovieDetailMapper
import com.example.movies.data.mappers.MovieMapper
import com.example.movies.data.mappers.toDomain
import com.example.movies.domain.entities.Movie
import com.example.movies.domain.entities.MovieDetail
import com.example.movies.domain.entities.MovieList
import com.example.movies.domain.entities.MovieRecommendationList
import com.example.movies.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val movieDTOMapper: MovieDTOMapper,
    private val movieORMMapper: MovieORMMapper,
    private val movieDetailDTOMapper: MovieDetailDTOMapper
) : MoviesRepository {
    override suspend fun getListMovies(page: Int?): Response<MovieList> {
        return try {
            val response = moviesRemoteDataSource.getListMovies(page ?: 1)
            val body = response.body()
            if (body != null && response.isSuccessful) {
                Response.Success(movieDTOMapper.fromMovieDto(body))
            } else {
                Response.Error("something went wrong")
            }
        } catch (error: Exception) {
            Response.Error(error.message.toString())
        }
    }

    override suspend fun getDetailMovie(id: Int): Response<MovieDetail> {
        return try {
            val response = moviesRemoteDataSource.getDetailMovie(id)
            val body = response.body()
            if (body != null && response.isSuccessful) {
                Response.Success(movieDetailDTOMapper.mapFromEntity(body))
            } else {
                Response.Error("something went wrong")
            }
        } catch (error: Exception) {
            Response.Error(error.message.toString())
        }
    }

    override suspend fun saveMovieToFavourites(movie: Movie): Response<Unit> {
        return try {
            moviesLocalDataSource.saveMovieToFavourites(movieORMMapper.mapToEntity(movie))
            return Response.Success(Unit)
        } catch (error: Exception) {
            Response.Error(error.message.toString())
        }
    }

    override suspend fun deleteMovieFromFavourites(id: Int): Response<Unit> {
        return try {
            moviesLocalDataSource.deleteFromFavourites(id)
            return Response.Success(Unit)
        } catch (error: Exception) {
            Response.Error(error.message.toString())
        }
    }

    override suspend fun getFavouritesMovies(): Response<Flow<List<Movie>>> {
        return try {
            val response = moviesLocalDataSource.getFavouritesMovies().map {
                movieORMMapper.fromEntityList(it)
            }
            Response.Success(response)
        } catch (error: Exception) {
            Response.Error(error.message.toString())
        }
    }

    override suspend fun getRecommendedMovies(id: Int, page: Int?): Response<MovieRecommendationList> {
        return try {
            val response = moviesRemoteDataSource.getRecommendationList(id, page ?: 1)
            val body = response.body()
            if (body != null && response.isSuccessful) {
                Response.Success(body.toDomain())
            } else {
                Response.Error("something went wrong")
            }
        } catch (error: Exception) {
            Response.Error(error.message.toString())
        }
    }
}