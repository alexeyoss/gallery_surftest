package com.oss.gallery.feature_posts.presentation.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentMainBinding
import com.oss.gallery.feature_posts.contract.navigator
import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.presentation.BaseMainFragments
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import com.oss.gallery.feature_posts.utils.collectOnLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseMainFragments(R.layout.fragment_main),
    MainFragmentRvOnClickListener {

    private lateinit var binding: FragmentMainBinding
    private val mAdapter = MainFragmentAdapter(this)

    private val viewModel by viewModels<MainFragmentViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        initRecycleView()
        initListeners()

        return binding.root
    }

    private fun initRecycleView() = with(binding) {
        mainScreenRv.apply {
            setHasFixedSize(true)

            (itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations = false

            layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
                    gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
                }

            adapter = mAdapter
        }
    }

    private fun initListeners() = with(binding) {
        viewModel.picturesUiStateFlow.collectOnLifecycle(this@MainFragment) { uiState ->
            when (uiState) {
                is MainUiStates.Loading -> Unit
                is MainUiStates.Success<*> -> {
                    mAdapter.submitList(uiState.data as List<BasePictureCachedEntity>)
                }
                is MainUiStates.Empty -> Unit
                is MainUiStates.Error<*> -> Unit
            }
        }


        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onPostClicked(data: BasePictureCachedEntity) {
        navigator().changeActivity(this)
    }

    override fun onLikeClicked(post: BasePictureCachedEntity) {
        viewModel.onLikeClicked(post)
    }
}