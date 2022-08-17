package com.oss.gallery.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.oss.gallery.R
import com.oss.gallery.data.model.BasePictureModel
import com.oss.gallery.databinding.FragmentPostDetailsBinding
import com.oss.gallery.ui.base_fragments.BaseMainFragments
import com.oss.gallery.utils.argumentNullable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : BaseMainFragments(R.layout.fragment_post_details) {

    private lateinit var binding: FragmentPostDetailsBinding

    private var _state by argumentNullable<BasePictureModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)

        savedInstanceState?.get(ARG_KEY)?.let {
            initViews(it as BasePictureModel)
        }

        arguments?.getParcelable<BasePictureModel>(ARG_KEY)?.let {
            initViews(it)
        }

        return binding.root
    }

    private fun initViews(source: BasePictureModel) = with(binding) {
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
        fun newInstance(postDetails: BasePictureModel): PostDetailsFragment =
            PostDetailsFragment().apply {
                this._state = postDetails
            }
    }
}