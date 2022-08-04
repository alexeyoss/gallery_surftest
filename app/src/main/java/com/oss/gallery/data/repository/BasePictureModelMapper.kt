package com.oss.gallery.data.repository

import com.oss.gallery.data.model.BasePictureModel
import com.oss.gallery.data.network.response.NetworkPictureResponse
import com.oss.gallery.utils.EntityMapper
import com.oss.gallery.utils.StringUtils.getFormattedDateFromTimestamp

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