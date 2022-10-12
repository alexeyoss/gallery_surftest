package com.oss.gallery.feature_posts.utils

import androidx.recyclerview.widget.DiffUtil
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity

object UniversalItemCallback : DiffUtil.ItemCallback<BasePictureCachedEntity>() {
    override fun areItemsTheSame(
        oldItem: BasePictureCachedEntity,
        newItem: BasePictureCachedEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BasePictureCachedEntity,
        newItem: BasePictureCachedEntity
    ): Boolean {
        return oldItem == newItem
    }
}