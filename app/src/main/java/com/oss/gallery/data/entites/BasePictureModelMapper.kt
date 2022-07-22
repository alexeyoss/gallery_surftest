package com.oss.gallery.data.entites

import com.oss.gallery.network.response.NetworkPictureModel
import com.oss.gallery.utils.EntityMapper
import com.oss.gallery.utils.StringUtils.getFormattedDateFromTimestamp

class BasePictureModelMapper : EntityMapper<NetworkPictureModel, BasePictureModel> {
    override fun mapModelFromEntity(entity: NetworkPictureModel): BasePictureModel {
        return BasePictureModel(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            photoUrl = entity.photoUrl,
            publicationDate = entity.publicationDate.getFormattedDateFromTimestamp(),
            liked = false
        )
    }

    override fun mapModelToEntity(entity: BasePictureModel): NetworkPictureModel {
        return NetworkPictureModel(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            photoUrl = entity.photoUrl,
            publicationDate = entity.publicationDate
        )
    }

    fun mapEntityList(entities: List<NetworkPictureModel>): List<BasePictureModel> {
        return entities.map { mapModelFromEntity(it) }
    }
}