package com.oss.gallery.feature_posts.data.database

import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsStorage
@Inject
constructor(
    private val dao: PostsDao,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getAll(): List<BasePictureCachedEntity> = withContext(ioDispatcher) {
        dao.getAll()
    }

    suspend fun getAllLikedPosts(): List<BasePictureCachedEntity> = withContext(ioDispatcher) {
        dao.getAllLikePosts()
    }

    suspend fun saveAllUniqueData(posts: List<BasePictureCachedEntity>) =
        withContext(ioDispatcher) {
            dao.saveAllUniqueData(posts)
        }

    suspend fun likePostWithTimeStamp(post: BasePictureCachedEntity) = withContext(ioDispatcher) {
        dao.likePostWithTimeStamp(post)
    }

    suspend fun unlikePostWithTimeStamp(post: BasePictureCachedEntity) = withContext(ioDispatcher) {
        dao.unlikePostWithTimeStamp(post)
    }
}