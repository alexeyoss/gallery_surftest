package com.oss.gallery.ui.favorites_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oss.gallery.data.entites.BasePictureModel
import com.oss.gallery.databinding.ItemFavoritesFragmentBinding
import com.oss.gallery.utils.FavoritesFragmentOnClickListener

class FavoritesFragmentAdapter(
    val clickListener: FavoritesFragmentOnClickListener
) : RecyclerView.Adapter<FavoritesFragmentAdapter.mViewHolder>() {

    private var data = listOf<BasePictureModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(
            ItemFavoritesFragmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.bind(data[position])
        holder.initListeners(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class mViewHolder(
        private val binding: ItemFavoritesFragmentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: BasePictureModel) = with(binding) {
            Glide.with(itemView.context)
                .load(data.photoUrl)
                .centerCrop()
                .into(photo)
            title.text = data.title
            publicationDate.text = data.publicationDate
            content.text = data.content
        }

        fun initListeners(data: BasePictureModel) = with(binding) {
            like.setOnClickListener {
                clickListener.onLikeClicked(
                    data.id,
                    isLiked = false
                ) // TODO build the data model with additional "like" status. The opposite of the data files
            }
        }
    }
}