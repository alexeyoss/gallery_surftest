package com.oss.gallery.feature_posts.domain.repository

import com.oss.gallery.feature_posts.data.model.BasePictureModel
import com.oss.gallery.feature_posts.data.network.request.NetworkRequestState

interface MainRepository {
    suspend fun logout(): NetworkRequestState<Unit>
    suspend fun getPicturesFromNetworkAndMapToBaseModel(): NetworkRequestState<List<BasePictureModel>>
    suspend fun cleanStorageResources(): Boolean
}