package com.oss.gallery.feature_posts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oss.gallery.BuildConfig
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity

@Database(
    entities = [BasePictureCachedEntity::class],
    version = BuildConfig.DB_VERSION,
    exportSchema = false
)
abstract class PostsDB : RoomDatabase() {

    abstract fun postsDao(): PostsDao
}