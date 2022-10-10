package com.oss.gallery.feature_posts.data.database

import androidx.room.Dao
import androidx.room.Query
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity

@Dao
interface PostsDao {

    @Query("SELECT * FROM cached_posts")
    suspend fun getAll(): List<BasePictureCachedEntity>


}