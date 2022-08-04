package com.oss.gallery.ui.favorites_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.oss.gallery.R
import com.oss.gallery.contract.ToolbarHandler
import com.oss.gallery.databinding.FragmentFavoritesBinding
import com.oss.gallery.ui.base_fragments.BaseMainFragments
import com.oss.gallery.utils.listeners.FavoritesFragmentOnClickListener

class FavoritesFragment : BaseMainFragments(R.layout.fragment_favorites),
    FavoritesFragmentOnClickListener,
    ToolbarHandler {

    private lateinit var binding: FragmentFavoritesBinding
    private val mAdapter = FavoritesFragmentAdapter(this)

    // TODO Add custom filed "dateOfAddition" into the DataStorage cause we need to sort the list.
    //  The sort operation we can do in out request from the DataStorage
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        initListeners()
        initRecycleView()

        return binding.root
    }

    private fun initListeners() = with(binding) {

    }

    private fun initRecycleView() = with(binding) {
        favoritesRv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    override fun onLikeClicked(pictureId: Int, isLiked: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getStringRes(): Int = R.string.favorites_title
}