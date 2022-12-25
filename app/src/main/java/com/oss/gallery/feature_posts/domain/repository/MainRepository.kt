package com.oss.gallery.feature_posts.domain.repository

import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_authorization.data.network.NetworkRequestState

interface MainRepository {
    suspend fun logout(): NetworkRequestState<Unit>
    suspend fun getPicturesFromNetworkAndMapToBasePictureCachedModel(): NetworkRequestState<List<BasePictureCachedEntity>>
    suspend fun cleanStorageResources(): Boolean
}