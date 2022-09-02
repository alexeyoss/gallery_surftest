package com.oss.gallery.ui.login_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.oss.gallery.R
import com.oss.gallery.contract.navigator
import com.oss.gallery.data.network.ApiService
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.databinding.FragmentLoginBinding
import com.oss.gallery.ui.base_fragments.BaseAuthFragments
import com.oss.gallery.ui.states.AuthUiStates
import com.oss.gallery.utils.collectOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseAuthFragments(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModelImpl by viewModels()

    @Inject
    lateinit var apiService: ApiService

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
            viewModel.login(getCredentialsFromUi())
        }

        // TODO Debug this part. Don't work listener
        viewModel.authUiStateFlow.collectOnLifecycle(this@LoginFragment) { uiState ->
            when (uiState) {
                is AuthUiStates.Success -> navigator().launchScreen()
                is AuthUiStates.Error -> {
                    // TODO Show SnackBar
                    loginBtn.loading = false
                }
                is AuthUiStates.Empty -> Unit
                is AuthUiStates.Loading -> Unit
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