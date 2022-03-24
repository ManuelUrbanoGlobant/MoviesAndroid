package com.example.movies.data.mappers

import com.example.movies.data.entities.MovieDto
import com.example.movies.data.utils.EntityMapper
import com.example.movies.domain.entities.Movie

class MovieMapper : EntityMapper<MovieDto, Movie> {
    override fun mapFromEntity(entity: MovieDto): Movie {
        with(entity) {
            return Movie(
                id = id,
                name = title,
                date = releaseDate,
                overview = overview,
                director = "",
                score = voteAverage,
                thumbnail = backdropPath
            )
        }
    }

    fun fromEntityList(initial: List<MovieDto>): List<Movie>{
        return initial.map { mapFromEntity(it) }
    }
}