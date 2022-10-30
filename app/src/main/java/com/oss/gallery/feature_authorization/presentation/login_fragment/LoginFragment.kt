package com.oss.gallery.feature_authorization.presentation.login_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import com.oss.gallery.R
import com.oss.gallery.databinding.FragmentLoginBinding
import com.oss.gallery.feature_authorization.data.network.request.NetworkAuthRequest
import com.oss.gallery.feature_authorization.presentation.BaseAuthFragments
import com.oss.gallery.feature_authorization.presentation.states.AuthUiStates
import com.oss.gallery.feature_authorization.presentation.states.ValidationState
import com.oss.gallery.feature_posts.contract.navigator
import com.oss.gallery.feature_posts.utils.collectOnLifecycle
import com.oss.gallery.feature_posts.utils.setErrorStateForTextInputLayout
import com.oss.gallery.feature_posts.utils.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginFragment : BaseAuthFragments(R.layout.fragment_login) {

    private var binding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModelImpl by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        this.binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListeners()
        initViews()
    }

    private fun initListeners() {
        // TODO loginEditText using Regex mask
        val binding = checkNotNull(binding)

        with(binding) {
            loginBtn.setOnClickListener {
                viewModel.validateLoginForm(getNetworkAuthRequestModel())
                freezeUiEntries(enabled = false)
            }

            loginInputEt.textChanges()
                .debounce(200)
                .onEach { refreshTextInputLayout(loginInputLayout) }
                .launchIn(lifecycleScope)

            passwordInputEt.textChanges()
                .debounce(200)
                .onEach { refreshTextInputLayout(passwordInputLayout) }
                .launchIn(lifecycleScope)

            viewModel.authUiStateFlow.collectOnLifecycle(this@LoginFragment) { uiState ->
                when (uiState) {
                    is AuthUiStates.Success<*> -> {
                        freezeUiEntries(true)
                        navigator().changeActivity(null)
                    }
                    is AuthUiStates.Error<*> -> {
                        freezeUiEntries(true)
                        loginBtn.loading = false
                        //TODO classify the Network Error or Login/Password Error
                    }
                    is AuthUiStates.Empty -> Unit
                    is AuthUiStates.Loading -> loginBtn.loading = true
                }

            }

            viewModel.loginValidationFlow.collectOnLifecycle(this@LoginFragment) { validationState ->
                when (validationState) {
                    is ValidationState.Initial -> Unit
                    is ValidationState.EmptyFiledError -> {
                        loginInputLayout.setErrorStateForTextInputLayout(
                            validationState.message
                        )
                    }
                    is ValidationState.IncorrectFiledError -> {
                        loginInputLayout.setErrorStateForTextInputLayout(
                            validationState.message
                        )
                        Toast.makeText(
                            context,
                            validationState.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    } // TODO replace by CustomSnackBar
                    is ValidationState.Successful -> Unit
                }
            }

            viewModel.passwordValidationFlow.collectOnLifecycle(this@LoginFragment) { validationState ->
                when (validationState) {
                    is ValidationState.Initial -> Unit
                    is ValidationState.EmptyFiledError -> {
                        passwordInputLayout.setErrorStateForTextInputLayout(
                            validationState.message
                        )
                    }
                    is ValidationState.IncorrectFiledError -> {
                        passwordInputLayout.setErrorStateForTextInputLayout(
                            validationState.message
                        )
                        Toast.makeText(
                            context,
                            validationState.message,
                            Toast.LENGTH_SHORT
                        ).show() // TODO replace by CustomSnackBar
                    }
                    is ValidationState.Successful -> Unit
                }
            }

            viewModel.commonValidationFlow.collectOnLifecycle(this@LoginFragment) { validationState ->
                freezeUiEntries(enabled = true)
                loginBtn.loading = false

                if (validationState) viewModel.login(getNetworkAuthRequestModel())
            }
        }
    }

    private fun refreshTextInputLayout(textInputLayout: TextInputLayout) {
        textInputLayout.apply {
            error = null
            boxStrokeColor = resources.getColor(R.color.black)
        }
    }

    private fun initViews() {
        val binding = checkNotNull(binding)
        with(binding) {
            loginInputLayout.isHelperTextEnabled = false
            passwordInputLayout.isHelperTextEnabled = false
        }
    }

    private fun getNetworkAuthRequestModel(): NetworkAuthRequest {
        val binding = checkNotNull(binding)
        return NetworkAuthRequest(
            with(binding) {
                loginInputLayout.prefixText.toString() + loginInputEt.text.toString()
                    .filter { it.isDigit() }
            },
            binding.passwordInputEt.text.toString()
        )
    }

    private fun freezeUiEntries(enabled: Boolean) {
        val binding = checkNotNull(binding)
        with(binding) {
            loginInputEt.isEnabled = enabled
            passwordInputEt.isEnabled = enabled
            loginBtn.isEnabled = enabled
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val binding = checkNotNull(binding)
        binding.loginBtn.setOnClickListener(null)
        this.binding = null
    }
}
