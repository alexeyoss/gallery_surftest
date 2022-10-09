package com.oss.gallery.feature_posts.utils

interface EntityMapper<FromModel, ToModel> {
    fun mapModelFromEntity(entity: FromModel): ToModel
    fun mapModelToEntity(entity: ToModel): FromModel
}