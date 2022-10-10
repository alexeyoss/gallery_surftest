package com.oss.gallery.feature_posts.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_posts")
data class BasePictureCachedEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val content: String,
    val photoUrl: String,
    val publicationDate: String,
    val liked: Boolean
)