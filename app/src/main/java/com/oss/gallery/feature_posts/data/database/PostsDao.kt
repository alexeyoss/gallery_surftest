package com.oss.gallery.feature_posts.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity

@Dao
abstract class PostsDao {

    @Query("SELECT * FROM cached_posts")
    abstract fun getAllCachedPosts(): List<BasePictureCachedEntity>

    @Query("SELECT * FROM cached_posts WHERE liked = 1 ORDER BY liked_at ASC ")
    abstract fun getAllLikePosts(): List<BasePictureCachedEntity>

    @Insert(onConflict = IGNORE)
    abstract fun saveAllUniqueData(posts: List<BasePictureCachedEntity>)

    @Insert(onConflict = REPLACE)
    protected abstract fun insertPost(post: BasePictureCachedEntity)

    fun unlikePostWithTimeStamp(post: BasePictureCachedEntity) {
        insertPost(post.apply {
            liked = false
            likeAt = null
        })
    }

    fun likePostWithTimeStamp(post: BasePictureCachedEntity) {
        insertPost(post.apply {
            liked = true
            likeAt = System.currentTimeMillis()
        })
    }
}


