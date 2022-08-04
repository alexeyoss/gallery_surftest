package com.oss.gallery.utils

interface EntityMapper<FromModel, ToModel> {
    fun mapModelFromEntity(entity: FromModel): ToModel
    fun mapModelToEntity(entity: ToModel): FromModel
}