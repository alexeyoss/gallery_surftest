package com.oss.gallery.feature_posts.data.repository

import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.data.network.response.NetworkPictureResponse
import com.oss.gallery.feature_posts.utils.EntityMapper
import com.oss.gallery.feature_posts.utils.StringUtils.getFormattedDateFromTimestamp

class BasePictureModelMapper : EntityMapper<NetworkPictureResponse, BasePictureCachedEntity> {
    override fun mapModelFromEntity(entity: NetworkPictureResponse): BasePictureCachedEntity {
        return BasePictureCachedEntity(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            photoUrl = entity.photoUrl,
            publicationDate = entity.publicationDate.getFormattedDateFromTimestamp(),
            liked = false,
            likeAt = null
        )
    }

    override fun mapModelToEntity(entity: BasePictureCachedEntity): NetworkPictureResponse {
        return NetworkPictureResponse(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            photoUrl = entity.photoUrl,
            publicationDate = entity.publicationDate
        )
    }

    fun mapEntityList(entities: List<NetworkPictureResponse>): List<BasePictureCachedEntity> {
        return entities.map { mapModelFromEntity(it) }
    }
}