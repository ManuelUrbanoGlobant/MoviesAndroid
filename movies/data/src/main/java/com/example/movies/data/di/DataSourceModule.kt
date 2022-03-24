package com.example.movies.data.di

import com.example.movies.data.BuildConfig
import com.example.movies.data.api.MoviesService
import com.example.movies.data.datasource.MoviesRemoteDataSource
import com.example.movies.data.datasource.MoviesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun providesMoviesRemoteDataSource(moviesService: MoviesService): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImpl(moviesService, BuildConfig.API_KEY)
    }
}