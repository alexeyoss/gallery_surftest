package com.oss.gallery.feature_posts.presentation.main_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oss.gallery.R
import com.oss.gallery.databinding.ItemMainFragmentBinding
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.utils.UniversalItemCallback

class MainFragmentAdapter(
    private val listener: MainFragmentRvOnClickListener
) : ListAdapter<BasePictureCachedEntity, MainFragmentAdapter.PictureHolder>(UniversalItemCallback),
    View.OnClickListener {

    override fun onClick(v: View) {
        val picture = v.tag as BasePictureCachedEntity
        when (v.id) {
            R.id.like -> listener.onLikeClicked(picture)
            else -> listener.onPostClicked(picture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val binding = ItemMainFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        with(binding) {
            root.setOnClickListener(this@MainFragmentAdapter)
            like.setOnClickListener(this@MainFragmentAdapter)
        }
        return PictureHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        val picture = getItem(position)

        with(holder.binding) {
            root.tag = picture
            like.tag = picture

            Glide.with(holder.itemView.context)
                .load(picture.photoUrl)
                .centerCrop()
                .into(photo)
            title.text = picture.title
            like.setImageResource(
                if (picture.liked)
                    R.drawable.heart_fill
                else
                    R.drawable.heart_empty
            )

        }
    }

    inner class PictureHolder(
        val binding: ItemMainFragmentBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
