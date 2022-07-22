package com.oss.gallery.ui.login_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.oss.gallery.R
import com.oss.gallery.contract.navigator
import com.oss.gallery.databinding.FragmentLoginBinding
import com.oss.gallery.ui.base_fragments.BaseAuthFragments

class LoginFragment : BaseAuthFragments(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val mViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        initListeners()
        initViews()

        return binding.root
    }

    private fun initListeners() = with(binding) {
        // TODO loginEditText using Regex mask

        // It STILL NOT WORKING
        loginBtn.setOnClickListener {
            loginBtn.loading = true
            navigator().launchScreen()
        }
    }

    private fun initViews() = with(binding) {
        loginInputLayout.isHelperTextEnabled = false
        passwordInputLayout.isHelperTextEnabled = false
    }

    override fun onDestroy() = with(binding) {
        super.onDestroy()
        loginBtn.setOnClickListener(null)
    }
}