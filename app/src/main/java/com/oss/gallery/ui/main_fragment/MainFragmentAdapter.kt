package com.oss.gallery.ui.main_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oss.gallery.R
import com.oss.gallery.databinding.ItemMainScreenBinding
import com.oss.gallery.network.PictureModel

class MainFragmentAdapter(
    val onClickRead: (PictureModel) -> Unit
) : RecyclerView.Adapter<MainFragmentAdapter.mViewHolder>() {

    private var data = listOf<PictureModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {
        return mViewHolder(
            ItemMainScreenBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<PictureModel>) {
        val diffCallBack = DiffCallback(data, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallBack, false)
        data = newData
        diffResult.dispatchUpdatesTo(this)
    }

    inner class mViewHolder(
        private val binding: ItemMainScreenBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PictureModel) = with(binding) {
            Glide.with(itemView.context)
                .load(data.photoUrl)
                .centerCrop()
                .into(photo)
            title.text = data.title
            like.setBackgroundResource(R.drawable.heart_empty)
            photo.setOnClickListener {
                onClickRead(data)
            }
        }
    }
}

class DiffCallback(
    private val oldData: List<PictureModel>,
    private val newData: List<PictureModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovie = oldData[oldItemPosition]
        val newMovie = newData[newItemPosition]
        return oldMovie.id == newMovie.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPictures = oldData[oldItemPosition]
        val newPictures = newData[newItemPosition]
        return oldPictures.title == newPictures.title
            && oldPictures.content == newPictures.content
    }
}