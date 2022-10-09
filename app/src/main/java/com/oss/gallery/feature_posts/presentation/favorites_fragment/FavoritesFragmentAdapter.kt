package com.oss.gallery.feature_posts.presentation.favorites_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oss.gallery.R
import com.oss.gallery.databinding.ItemFavoritesFragmentBinding
import com.oss.gallery.feature_posts.data.model.BasePictureModel
import com.oss.gallery.feature_posts.utils.UniversalItemCallback

class FavoritesFragmentAdapter(
    private val listener: FavoritesFragmentOnClickListener
) : ListAdapter<BasePictureModel, FavoritesFragmentAdapter.FavoritesPosts>(UniversalItemCallback),
    View.OnClickListener {

    override fun onClick(v: View) {
        val picture = v.tag as BasePictureModel
        when (v.id) {
            R.id.like -> listener.onLikeClicked(picture)
            else -> Unit
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesPosts {
        val binding = ItemFavoritesFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        with(binding) {
            like.setOnClickListener(this@FavoritesFragmentAdapter)
        }
        return FavoritesPosts(binding)
    }

    override fun onBindViewHolder(holder: FavoritesPosts, position: Int) {
        val post = getItem(position)

        with(holder.binding) {
            like.tag = post

            Glide.with(holder.itemView.context)
                .load(post.photoUrl)
                .centerCrop()
                .into(photo)
            title.text = post.title
            publicationDate.text = post.publicationDate
            content.text = post.content
            like.setImageResource(
                if (post.liked)
                    R.drawable.heart_fill
                else
                    R.drawable.heart_empty
            )
        }
    }

    inner class FavoritesPosts(
        val binding: ItemFavoritesFragmentBinding
    ) : RecyclerView.ViewHolder(binding.root)
}