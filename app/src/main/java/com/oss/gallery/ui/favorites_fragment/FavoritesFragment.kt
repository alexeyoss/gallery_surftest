package com.oss.gallery.ui.favorites_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentFavoritesBinding
import com.oss.gallery.ui.base_fragments.BaseMainFragments

class FavoritesFragment : BaseMainFragments(R.layout.fragment_favorites) {

    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        initListeners()
        initViews()

        return binding.root
    }

    private fun initListeners() = with(binding) {

    }

    private fun initViews() = with(binding) {

    }
}