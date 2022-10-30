package com.oss.gallery.feature_posts.data.database

import com.oss.gallery.di.CoroutinesModule
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsStorage
@Inject
constructor(
    private val dao: PostsDao,
    @CoroutinesModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getAllCachedPosts(): List<BasePictureCachedEntity> = withContext(ioDispatcher) {
        dao.getAllCachedPosts()
    }

    suspend fun getAllLikedPosts(): List<BasePictureCachedEntity> = withContext(ioDispatcher) {
        dao.getAllLikePosts()
    }

    suspend fun saveAllUniqueData(posts: List<BasePictureCachedEntity>): Boolean {
        return withContext(Dispatchers.IO) {
            runCatching {
                dao.saveAllUniqueData(posts)
                return@runCatching true
            }.getOrElse {
                return@getOrElse false
            }
        }
    }

    suspend fun likePostWithTimeStamp(post: BasePictureCachedEntity) = withContext(ioDispatcher) {
        dao.likePostWithTimeStamp(post)
    }

    suspend fun unlikePostWithTimeStamp(post: BasePictureCachedEntity) = withContext(ioDispatcher) {
        dao.unlikePostWithTimeStamp(post)
    }
}

