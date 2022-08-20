package com.oss.gallery.ui.login_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.oss.gallery.R
import com.oss.gallery.contract.navigator
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.databinding.FragmentLoginBinding
import com.oss.gallery.ui.base_fragments.BaseAuthFragments
import com.oss.gallery.ui.states.AuthUiStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseAuthFragments(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val mViewModel: LoginViewModelImpl by viewModels()

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
        loginBtn.setOnClickListener {
            mViewModel.login(getCredentialsFromUi())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.authUiStateFlow.collect { uiState ->
                    when (uiState) {
                        is AuthUiStates.Success -> navigator().launchScreen()
                        is AuthUiStates.Error -> {
                            loginBtn.loading = false
                            // Show SnackBar
                        }
                        is AuthUiStates.Empty -> Unit
                        is AuthUiStates.Loading -> Unit
                    }
                }
            }
        }
    }

    private fun initViews() = with(binding) {
        loginInputLayout.isHelperTextEnabled = false
        passwordInputLayout.isHelperTextEnabled = false
    }

    private fun getCredentialsFromUi(): NetworkAuthRequest =
        // TODO validation process
        NetworkAuthRequest(
            with(binding) {
                loginInputLayout.prefixText.toString() + loginInputEt.text.toString()
                    .filter { it.isDigit() }
            },
            binding.passwordInputEt.text.toString()
        )

    override fun onDestroy() = with(binding) {
        super.onDestroy()
        loginBtn.setOnClickListener(null)
    }
}