package com.oss.gallery.feature_posts.data.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "cached_posts")
@Parcelize
data class BasePictureCachedEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "photo_url") var photoUrl: String,
    @ColumnInfo(name = "publication_date") var publicationDate: String,
    @ColumnInfo(name = "liked") var liked: Boolean,
    @ColumnInfo(name = "liked_at") var likeAt: Long?
) : Parcelable