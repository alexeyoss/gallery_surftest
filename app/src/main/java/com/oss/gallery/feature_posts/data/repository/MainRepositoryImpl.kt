package com.oss.gallery.feature_posts.data.repository

import com.oss.gallery.feature_authorization.data.storage.TokenStorage
import com.oss.gallery.feature_posts.data.model.BasePictureModel
import com.oss.gallery.feature_posts.data.network.MainApiService
import com.oss.gallery.feature_posts.data.network.request.NetworkRequestState
import com.oss.gallery.feature_posts.domain.repository.MainRepository
import com.oss.gallery.feature_posts.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl
@Inject
constructor(
    private val mainApiService: MainApiService,
    private val tokenStorage: TokenStorage,
    private val basePictureModel: BasePictureModelMapper
) : MainRepository {

    override suspend fun logout(): NetworkRequestState<Unit> {
        return safeApiCall {
            mainApiService.logout()
        }
    }

    override suspend fun getPicturesFromNetworkAndMapToBaseModel(): NetworkRequestState<List<BasePictureModel>> {
        return safeApiCall {
            basePictureModel.mapEntityList(mainApiService.getPictures())
        }
    }

    override suspend fun cleanStorageResources(): Boolean = tokenStorage.deleteToken()
}