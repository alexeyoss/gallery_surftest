package com.oss.gallery.ui.profile_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentProfileBinding
import com.oss.gallery.ui.base_fragments.BaseMainFragments

class ProfileFragment : BaseMainFragments(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        initListeners()

        return binding.root
    }

    private fun initListeners() = with(binding) {
        logoutBtn.setOnClickListener {
            logoutBtn.loading = !logoutBtn.loading
            // TODO
        }
    }
}