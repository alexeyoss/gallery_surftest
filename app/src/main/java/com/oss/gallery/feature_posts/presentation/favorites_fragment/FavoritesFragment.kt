package com.oss.gallery.feature_posts.presentation.favorites_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentFavoritesBinding
import com.oss.gallery.feature_posts.presentation.utils.ToolbarHandler
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.presentation.BaseMainFragments
import com.oss.gallery.feature_posts.presentation.main_fragment.MainFragmentViewModelImpl
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import com.oss.gallery.feature_posts.utils.AlertDialogBuilder
import com.oss.gallery.feature_posts.utils.collectOnLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : BaseMainFragments(R.layout.fragment_favorites),
    FavoritesFragmentOnClickListener,
    ToolbarHandler {

    private lateinit var binding: FragmentFavoritesBinding
    private val mAdapter = FavoritesFragmentAdapter(this)
    private val viewModel by viewModels<MainFragmentViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListeners()
        initRecycleView()
        viewModel.getFavoritesPosts()
    }

    private fun initListeners() {
        viewModel.favoritesPostsUiStateFlow.collectOnLifecycle(this@FavoritesFragment) { uiState ->
            when (uiState) {
                is MainUiStates.Loading -> Unit
                is MainUiStates.Success<*> -> {
                    mAdapter.submitList(uiState.data as List<BasePictureCachedEntity>)
                }
                is MainUiStates.Empty -> {
                    mAdapter.submitList(emptyList<BasePictureCachedEntity>())
                }
                is MainUiStates.Error<*> -> Unit
            }
        }
    }

    private fun initRecycleView() = with(binding) {
        favoritesRv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    override fun onLikeClicked(post: BasePictureCachedEntity) {
        AlertDialogBuilder.createDialog(
            context = requireContext(),
            message = R.string.delete_post,
            onPositive = {
                viewModel.deleteFavoritePost(post)
            },
            onNegative = { }
        ).show()
    }

    override fun getStringRes(): Int = R.string.favorites_title
}