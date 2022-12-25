package com.oss.gallery.feature_posts.data.repository

import com.oss.gallery.feature_authorization.data.storage.TokenStorage
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.data.network.MainApiService
import com.oss.gallery.feature_authorization.data.network.NetworkRequestState
import com.oss.gallery.feature_posts.domain.repository.MainRepository
import com.oss.gallery.feature_posts.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl
@Inject
constructor(
    private val mainApiService: Provider<MainApiService>,
    private val tokenStorage: dagger.Lazy<TokenStorage>,
    private val pictureModelMapper: dagger.Lazy<PictureModelMapper>
) : MainRepository {

    override suspend fun logout(): NetworkRequestState<Unit> {
        return safeApiCall {
            mainApiService.get().logout()
        }
    }

    override suspend fun getPicturesFromNetworkAndMapToBasePictureCachedModel()
        : NetworkRequestState<List<BasePictureCachedEntity>> {
        return safeApiCall {
            mainApiService.get().getPictures().map {
                pictureModelMapper.get().mapModelFromEntity(it)
            }
        }
    }

    override suspend fun cleanStorageResources(): Boolean = tokenStorage.get().deleteToken()
}