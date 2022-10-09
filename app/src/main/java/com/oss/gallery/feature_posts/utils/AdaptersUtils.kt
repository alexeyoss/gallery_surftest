package com.oss.gallery.feature_posts.utils

import androidx.recyclerview.widget.DiffUtil
import com.oss.gallery.feature_posts.data.model.BasePictureModel

object UniversalItemCallback : DiffUtil.ItemCallback<BasePictureModel>() {
    override fun areItemsTheSame(
        oldItem: BasePictureModel,
        newItem: BasePictureModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BasePictureModel,
        newItem: BasePictureModel
    ): Boolean {
        return oldItem == newItem
    }
}