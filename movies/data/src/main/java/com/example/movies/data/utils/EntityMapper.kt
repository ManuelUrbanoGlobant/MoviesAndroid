package com.example.movies.data.utils

interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    //TODO this function will not be used in our project
    //fun mapToEntity(domainModel: DomainModel): Entity
}