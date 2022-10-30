package com.oss.gallery.feature_posts.data.repository

import com.oss.gallery.feature_posts.data.database.PostsStorage
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.domain.repository.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepositoryImpl
@Inject
constructor(
    private val postsStorage: PostsStorage
) : PostsRepository {
    override suspend fun likePostWithTimeStamp(post: BasePictureCachedEntity) {
        postsStorage.likePostWithTimeStamp(post)
    }

    override suspend fun unlikePostWithTimeStamp(post: BasePictureCachedEntity) {
        postsStorage.unlikePostWithTimeStamp(post)
    }

    override suspend fun getAllCachedPostsFromDb(): List<BasePictureCachedEntity> =
        postsStorage.getAllCachedPosts()

    override suspend fun getFavoritesPosts(): List<BasePictureCachedEntity> {
        return postsStorage.getAllLikedPosts()
    }

    override suspend fun saveAllUniqueData(posts: List<BasePictureCachedEntity>): Boolean {
        return postsStorage.saveAllUniqueData(posts)
    }
}