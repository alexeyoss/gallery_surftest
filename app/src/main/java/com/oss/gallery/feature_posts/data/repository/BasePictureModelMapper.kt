package com.oss.gallery.feature_posts.data.repository

import com.oss.gallery.feature_posts.data.model.BasePictureModel
import com.oss.gallery.feature_posts.data.network.response.NetworkPictureResponse
import com.oss.gallery.feature_posts.utils.EntityMapper
import com.oss.gallery.feature_posts.utils.StringUtils.getFormattedDateFromTimestamp

class BasePictureModelMapper : EntityMapper<NetworkPictureResponse, BasePictureModel> {
    override fun mapModelFromEntity(entity: NetworkPictureResponse): BasePictureModel {
        return BasePictureModel(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            photoUrl = entity.photoUrl,
            publicationDate = entity.publicationDate.getFormattedDateFromTimestamp(),
            liked = false
        )
    }

    override fun mapModelToEntity(entity: BasePictureModel): NetworkPictureResponse {
        return NetworkPictureResponse(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            photoUrl = entity.photoUrl,
            publicationDate = entity.publicationDate
        )
    }

    fun mapEntityList(entities: List<NetworkPictureResponse>): List<BasePictureModel> {
        return entities.map { mapModelFromEntity(it) }
    }
}