package com.example.movies.data.mappers

import com.example.movies.data.entities.dto.MovieDTO
import com.example.movies.data.utils.EntityMapper
import com.example.movies.domain.entities.Movie

class MovieDTOMapper : EntityMapper<MovieDTO, Movie> {
    override fun mapFromEntity(entity: MovieDTO): Movie {
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

    fun fromEntityList(initial: List<MovieDTO>): List<Movie>{
        return initial.map { mapFromEntity(it) }
    }

    override fun mapToEntity(domainModel: Movie): MovieDTO {
        with(domainModel) {
            return MovieDTO(
                id = id,
                title = name,
                releaseDate = date,
                overview = overview,
                voteAverage = score,
                backdropPath = thumbnail
            )
        }
    }
}