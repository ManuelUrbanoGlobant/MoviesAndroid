package com.example.movies.di

import android.content.Context
import androidx.room.Room
import com.example.movies.data.BuildConfig
import com.example.movies.data.api.MoviesService
import com.example.movies.data.datasource.MoviesLocalDataSource
import com.example.movies.data.datasource.MoviesLocalDataSourceImpl
import com.example.movies.data.datasource.MoviesRemoteDataSource
import com.example.movies.data.datasource.MoviesRemoteDataSourceImpl
import com.example.movies.data.db.MovieDatabase
import com.example.movies.data.mappers.MovieDetailMapper
import com.example.movies.data.mappers.MovieDTOMapper
import com.example.movies.data.mappers.MovieORMMapper
import com.example.movies.data.repositories.MoviesRepositoryImpl
import com.example.movies.domain.repositories.MoviesRepository
import com.example.movies.domain.usecases.GetDetailMovieUseCase
import com.example.movies.domain.usecases.GetDetailMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideMovieDatabase(@ApplicationContext context: Context) : MovieDatabase = Room.databaseBuilder(context,)

    @Singleton
    @Provides
    fun provideMovieDao()

    @Singleton
    @Provides
    fun provideMoviesRemoteDataSource(moviesService: MoviesService): MoviesRemoteDataSource =
        MoviesRemoteDataSourceImpl(moviesService, BuildConfig.API_KEY)

    @Singleton
    @Provides
    fun provideMoviesLocalDataSource(moviesService: MoviesService): MoviesLocalDataSource =
        MoviesLocalDataSourceImpl()

    @Singleton
    @Provides
    fun provideMovieDTOMapper(): MovieDTOMapper = MovieDTOMapper()

    @Singleton
    @Provides
    fun provideMovieORMMapper(): MovieORMMapper = MovieORMMapper()

    @Singleton
    @Provides
    fun provideMovieDetailMapper(): MovieDetailMapper = MovieDetailMapper()

    @Singleton
    @Provides
    fun providesMoviesRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        movieDTOMapper: MovieDTOMapper,
        movieDetailMapper: MovieDetailMapper
    ): MoviesRepository = MoviesRepositoryImpl(
        moviesRemoteDataSource,
        movieDTOMapper,
        movieDetailMapper
    )

    @Provides
    fun provideGetDetailMovieUseCase(repository: MoviesRepository): GetDetailMovieUseCase = GetDetailMovieUseCaseImpl(repository)
}