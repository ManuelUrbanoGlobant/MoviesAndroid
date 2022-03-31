package com.example.movies.di

import android.content.Context
import androidx.room.Room
import com.example.movies.data.BuildConfig
import com.example.movies.data.api.MoviesService
import com.example.movies.data.datasource.MoviesLocalDataSource
import com.example.movies.data.datasource.MoviesLocalDataSourceImpl
import com.example.movies.data.datasource.MoviesRemoteDataSource
import com.example.movies.data.datasource.MoviesRemoteDataSourceImpl
import com.example.movies.data.db.MovieDao
import com.example.movies.data.db.MovieDatabase
import com.example.movies.data.mappers.MovieDetailDTOMapper
import com.example.movies.data.mappers.MovieDTOMapper
import com.example.movies.data.mappers.MovieORMMapper
import com.example.movies.data.repositories.MoviesRepositoryImpl
import com.example.movies.domain.repositories.MoviesRepository
import com.example.movies.domain.usecases.GetDetailMovieUseCase
import com.example.movies.domain.usecases.GetDetailMovieUseCaseImpl
import com.example.movies.domain.usecases.GetMovieRecommendationsUseCase
import com.example.movies.domain.usecases.GetMovieRecommendationsUseCaseImpl
import com.example.movies.domain.usecases.*
import com.example.movies.domain.usecases.movieDetail.GetDetailMovieUseCase
import com.example.movies.domain.usecases.movieDetail.GetDetailMovieUseCaseImpl
import com.example.movies.domain.usecases.moviesList.GetMoviesListUseCase
import com.example.movies.domain.usecases.moviesList.GetMoviesListUseCaseImpl
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
    fun provideMoviesService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, "movies").build()

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()



    @Provides
    fun provideMoviesRemoteDataSource(moviesService: MoviesService): MoviesRemoteDataSource =
        MoviesRemoteDataSourceImpl(moviesService, BuildConfig.API_KEY)


    @Provides
    fun provideMoviesLocalDataSource(movieDao: MovieDao): MoviesLocalDataSource =
        MoviesLocalDataSourceImpl(movieDao)


    @Provides
    fun provideMovieDTOMapper(): MovieDTOMapper = MovieDTOMapper()


    @Provides
    fun provideMovieORMMapper(): MovieORMMapper = MovieORMMapper()


    @Provides
    fun provideMovieDetailMapper(): MovieDetailDTOMapper = MovieDetailDTOMapper()


    @Provides
    fun providesMoviesRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesLocalDataSource: MoviesLocalDataSource,
        movieDTOMapper: MovieDTOMapper,
        movieORMMapper: MovieORMMapper,
        movieDetailDTOMapper: MovieDetailDTOMapper
    ): MoviesRepository = MoviesRepositoryImpl(
        moviesRemoteDataSource,
        moviesLocalDataSource,
        movieDTOMapper,
        movieORMMapper,
        movieDetailDTOMapper
    )

    @Provides
    fun provideGetDetailMovieUseCase(repository: MoviesRepository): GetDetailMovieUseCase =
        GetDetailMovieUseCaseImpl(repository)

    @Provides
    fun provideGetDetailMovieRecommendationUseCase(repository: MoviesRepository): GetMovieRecommendationsUseCase = GetMovieRecommendationsUseCaseImpl(repository)

    @Provides
    fun provideSaveMovieToFavouritesUseCase(repository: MoviesRepository): SaveMovieToFavouritesUseCase =
        SaveMovieToFavouritesUseCaseImpl(repository)

    @Provides
    fun provideDeleteMovieFromFavouritesUseCase(repository: MoviesRepository): DeleteMovieFromFavouritesUseCase =
        DeleteMovieFromFavouritesUseCaseImpl(repository)

    @Provides
    fun provideGetFavouritesMoviesUseCase(repository: MoviesRepository): GetFavouritesMoviesUseCase =
        GetFavouritesMoviesUseCaseImpl(repository)

    @Provides
    fun provideGetMoviesListUseCase(repository: MoviesRepository) : GetMoviesListUseCase = GetMoviesListUseCaseImpl(repository)
}