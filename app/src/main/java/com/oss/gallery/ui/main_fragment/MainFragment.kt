package com.oss.gallery.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oss.gallery.R
import com.oss.gallery.data.model.BasePictureModel
import com.oss.gallery.databinding.FragmentMainBinding
import com.oss.gallery.ui.base_fragments.BaseMainFragments
import com.oss.gallery.utils.listeners.MainFragmentOnClickListener

class MainFragment : BaseMainFragments(R.layout.fragment_main),
    MainFragmentOnClickListener {

    private lateinit var binding: FragmentMainBinding
    private val mAdapter = MainFragmentAdapter(this)

    private val viewModel by viewModels<MainFragmentViewModelImpl>()

    init {
    }

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
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            // TODO determinate the margins between the items
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun initListeners() = with(binding) {
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onViewClicked(data: BasePictureModel) {
        TODO("Not yet implemented")
    }

    override fun onLikeClicked(pictureId: Int, isLiked: Boolean) {
        TODO("Not yet implemented")
    }
}