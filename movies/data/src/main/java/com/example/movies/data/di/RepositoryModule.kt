package com.example.movies.data.di

import com.example.movies.data.datasource.MoviesRemoteDataSource
import com.example.movies.data.mappers.MovieDetailMapper
import com.example.movies.data.mappers.MovieMapper
import com.example.movies.data.repositories.MoviesRepositoryImpl
import com.example.movies.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesMoviesRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        movieMapper: MovieMapper,
        movieDetailMapper: MovieDetailMapper
    ): MoviesRepository {
        return MoviesRepositoryImpl(
            moviesRemoteDataSource,
            movieMapper,
            movieDetailMapper
        )
    }
}