package com.example.movies.data.mappers

import com.example.movies.data.entities.dto.MovieDetailDTO
import com.example.movies.data.utils.EntityMapper
import com.example.movies.domain.entities.MovieDetail

class MovieDetailDTOMapper : EntityMapper<MovieDetailDTO, MovieDetail> {
    override fun mapFromEntity(entity: MovieDetailDTO): MovieDetail {
        with(entity) {
            return MovieDetail(
                id = id,
                name = title,
                date = releaseDate,
                overview = overview,
                //TODO the schema from API doesn't have the director field
                director = "",
                score = voteAverage,
                thumbnail = backdropPath,
                time = runtime
            )
        }
    }

    override fun mapToEntity(domainModel: MovieDetail): MovieDetailDTO {
        with(domainModel) {
            return MovieDetailDTO(
                id = id,
                title = name,
                releaseDate = date,
                overview = overview,
                voteAverage = score,
                backdropPath = thumbnail,
                runtime = time
            )
        }
    }
}