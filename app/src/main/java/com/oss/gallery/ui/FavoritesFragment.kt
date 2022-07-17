package com.oss.gallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oss.gallery.R
import com.oss.gallery.ui.base_fragments.BaseMainFragments

class FavoritesFragment : BaseMainFragments(R.layout.fragment_favorites) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }
}