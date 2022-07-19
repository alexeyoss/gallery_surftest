package com.oss.gallery.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentPostDetailsBinding
import com.oss.gallery.ui.base_fragments.BaseMainFragments

class PostDetailsFragment : BaseMainFragments(R.layout.fragment_post_details) {

    private lateinit var binding: FragmentPostDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)


        return binding.root
    }

    private fun initViews() = with(binding) {
        // TODO info exchange via fragments API (extend the navigator functionality)
//        navigator().listenResult(Results::class.java, viewLifecycleOwner) { source ->
//            this@MovieDetailsFragment.result = source
//            initViews(source)
//        }
    }
}