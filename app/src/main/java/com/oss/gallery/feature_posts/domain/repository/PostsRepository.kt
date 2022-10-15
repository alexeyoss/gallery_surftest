package com.oss.gallery.feature_posts.domain.repository

import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity

interface PostsRepository {
    suspend fun likePostWithTimeStamp(post: BasePictureCachedEntity)
    suspend fun unlikePostWithTimeStamp(post: BasePictureCachedEntity)
    suspend fun getAllCachedPostsFromDb(): List<BasePictureCachedEntity>
    suspend fun saveAllUniqueData(posts: List<BasePictureCachedEntity>): Boolean
}