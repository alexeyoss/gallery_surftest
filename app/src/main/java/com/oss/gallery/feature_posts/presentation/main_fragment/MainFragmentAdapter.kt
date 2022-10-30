package com.oss.gallery.feature_posts.presentation.main_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oss.gallery.R
import com.oss.gallery.databinding.ItemMainFragmentBinding
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.utils.UniversalItemCallback

class MainFragmentAdapter(
    private val listener: MainFragmentRvOnClickListener
) : ListAdapter<BasePictureCachedEntity, MainFragmentAdapter.PictureHolder>(UniversalItemCallback()),
    View.OnClickListener {

    override fun onClick(v: View) {
        val post = v.tag as BasePictureCachedEntity
        when (v.id) {
            R.id.like -> listener.onLikeClicked(post)
            else -> listener.onPostClicked(post)
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
        holder.onBind(currentList[position])
    }

    inner class PictureHolder(
        val binding: ItemMainFragmentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: BasePictureCachedEntity) {
            with(binding) {
                root.tag = item
                like.tag = item

                Glide.with(itemView.context)
                    .load(item.photoUrl)
                    .centerCrop()
                    .into(photo)

                title.text = item.title
                like.setLiked(item.liked)
            }
        }

        private fun ImageView.setLiked(isLiked: Boolean) {
            when (isLiked) {
                true -> setImageResource(R.drawable.heart_fill)
                false -> setImageResource(R.drawable.heart_empty)
            }
        }
    }
}

