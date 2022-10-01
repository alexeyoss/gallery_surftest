package com.oss.gallery.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oss.gallery.R
import com.oss.gallery.data.network.response.NetworkPictureResponse
import com.oss.gallery.databinding.FragmentMainBinding
import com.oss.gallery.ui.base_fragments.BaseMainFragments
import com.oss.gallery.ui.states.main_activity_states.MainUiStates
import com.oss.gallery.utils.collectOnLifecycle
import com.oss.gallery.utils.listeners.MainFragmentOnClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseMainFragments(R.layout.fragment_main),
    MainFragmentOnClickListener {

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
                    mAdapter.setData(uiState.data as List<NetworkPictureResponse>)
                }
                is MainUiStates.Empty -> Unit
                is MainUiStates.Error<*> -> Unit
            }
        }


        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onViewClicked(data: NetworkPictureResponse) {
    }

    override fun onLikeClicked(pictureId: Int, isLiked: Boolean) {
        TODO("Not yet implemented")
    }
}