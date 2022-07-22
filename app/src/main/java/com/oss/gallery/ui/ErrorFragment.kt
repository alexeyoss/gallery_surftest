package com.oss.gallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentErrorBinding
import com.oss.gallery.ui.base_fragments.BaseMainFragments

class ErrorFragment : BaseMainFragments(R.layout.fragment_error) {

    private lateinit var binding: FragmentErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentErrorBinding.inflate(inflater, container, false)

        initListeners()

        return binding.root
    }

    private fun initListeners() = with(binding) {
        refreshButton.setOnClickListener {
            // TODO
        }
    }
}