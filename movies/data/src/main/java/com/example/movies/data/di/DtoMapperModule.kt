package com.example.movies.data.di

import com.example.movies.data.mappers.MovieDetailMapper
import com.example.movies.data.mappers.MovieMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DtoMapperModule {
    @Singleton
    @Provides
    fun provideMovieMapper(): MovieMapper {
        return MovieMapper()
    }

    @Singleton
    @Provides
    fun provideMovieDetailMapper(): MovieDetailMapper {
        return MovieDetailMapper()
    }
}