package com.oss.gallery.feature_posts.presentation.postdetails_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentPostDetailsBinding
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.presentation.BaseMainFragments
import com.oss.gallery.feature_posts.utils.argumentNullable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : BaseMainFragments(R.layout.fragment_post_details) {

    private lateinit var binding: FragmentPostDetailsBinding

    private var _state by argumentNullable<BasePictureCachedEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)

        savedInstanceState?.get(ARG_KEY)?.let {
            initViews(it as BasePictureCachedEntity)
        }

        arguments?.getParcelable<BasePictureCachedEntity>(ARG_KEY)?.let {
            initViews(it)
        }

        return binding.root
    }

    private fun initViews(source: BasePictureCachedEntity) = with(binding) {
        _state = source

        Glide.with(root)
            .load(source.photoUrl)
            .centerCrop()
            .into(photo)

        title.text = source.title
        publicationDate.text = source.publicationDate
        content.text = source.content
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(ARG_KEY, _state)
    }

    companion object {

        @JvmStatic
        private val ARG_KEY: String = PostDetailsFragment::class.java.name

        @JvmStatic
        fun newInstance(postDetails: BasePictureCachedEntity): PostDetailsFragment =
            PostDetailsFragment().apply {
                this._state = postDetails
            }
    }
}