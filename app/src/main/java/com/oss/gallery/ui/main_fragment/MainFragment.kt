package com.oss.gallery.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentMainBinding
import com.oss.gallery.ui.base_fragments.BaseMainFragments

class MainFragment : BaseMainFragments(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private val mAdapter = MainFragmentAdapter {
        findNavController().navigate(R.layout.fragment_post_details)
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
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun initListeners() = with(binding) {
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
    }
}