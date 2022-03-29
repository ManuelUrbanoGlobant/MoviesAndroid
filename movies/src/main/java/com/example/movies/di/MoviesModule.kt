package com.example.movies.di

import com.example.movies.data.BuildConfig
import com.example.movies.data.api.MoviesService
import com.example.movies.data.datasource.MoviesRemoteDataSource
import com.example.movies.data.datasource.MoviesRemoteDataSourceImpl
import com.example.movies.data.mappers.MovieDetailMapper
import com.example.movies.data.mappers.MovieMapper
import com.example.movies.data.repositories.MoviesRepositoryImpl
import com.example.movies.domain.repositories.MoviesRepository
import com.example.movies.domain.usecases.GetDetailMovieUseCase
import com.example.movies.domain.usecases.GetDetailMovieUseCaseImpl
import com.example.movies.domain.usecases.GetMovieRecommendationsUseCase
import com.example.movies.domain.usecases.GetMovieRecommendationsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Singleton
    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesService = retrofit.create(MoviesService::class.java)

    @Singleton
    @Provides
    fun providesMoviesRemoteDataSource(moviesService: MoviesService): MoviesRemoteDataSource =
        MoviesRemoteDataSourceImpl(moviesService, BuildConfig.API_KEY)

    @Singleton
    @Provides
    fun provideMovieMapper(): MovieMapper = MovieMapper()

    @Singleton
    @Provides
    fun provideMovieDetailMapper(): MovieDetailMapper = MovieDetailMapper()

    @Singleton
    @Provides
    fun providesMoviesRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        movieMapper: MovieMapper,
        movieDetailMapper: MovieDetailMapper
    ): MoviesRepository = MoviesRepositoryImpl(
        moviesRemoteDataSource,
        movieMapper,
        movieDetailMapper
    )

    @Provides
    fun provideGetDetailMovieUseCase(repository: MoviesRepository): GetDetailMovieUseCase = GetDetailMovieUseCaseImpl(repository)

    @Provides
    fun provideGetDetailMovieRecommendationUseCase(repository: MoviesRepository): GetMovieRecommendationsUseCase = GetMovieRecommendationsUseCaseImpl(repository)
}