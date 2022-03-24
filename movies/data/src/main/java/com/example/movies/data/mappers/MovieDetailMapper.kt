package com.example.movies.data.mappers

import com.example.movies.data.entities.MovieDetailDto
import com.example.movies.data.utils.EntityMapper
import com.example.movies.domain.entities.MovieDetail

class MovieDetailMapper : EntityMapper<MovieDetailDto, MovieDetail> {
    override fun mapFromEntity(entity: MovieDetailDto): MovieDetail {
        with(entity) {
            return MovieDetail(
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
}