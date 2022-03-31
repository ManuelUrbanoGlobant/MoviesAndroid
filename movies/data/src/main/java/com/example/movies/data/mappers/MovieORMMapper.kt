package com.example.movies.data.mappers

import com.example.movies.data.entities.orm.MovieORM
import com.example.movies.data.utils.EntityMapper
import com.example.movies.domain.entities.Movie

class MovieORMMapper : EntityMapper<MovieORM, Movie> {
    override fun mapFromEntity(entity: MovieORM): Movie {
        with(entity) {
            return Movie(
                id = id,
                name = title,
                date = releaseDate,
                overview = overview,
                score = voteAverage,
                thumbnail = backdropPath
            )
        }
    }

    fun fromEntityList(initial: List<MovieORM>): List<Movie> =
        initial.map { mapFromEntity(it) }


    override fun mapToEntity(domainModel: Movie): MovieORM {
        with(domainModel) {
            return MovieORM(
                id = id,
                title = name,
                releaseDate = date,
                overview = overview,
                voteAverage = score,
                backdropPath = thumbnail ?: ""
            )
        }
    }
}