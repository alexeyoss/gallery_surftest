package com.oss.gallery.feature_posts.data.database

import android.content.Context
import androidx.room.Room
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsStorage(context: Context) {
    private val database = Room.databaseBuilder(
        context, MainDataBase::class.java, "cached_posts"
    ).build()

    private val dao = database.postsDao()

    suspend fun getAll(): List<BasePictureCachedEntity> = withContext(Dispatchers.IO) {
        dao.getAll()
    }
}