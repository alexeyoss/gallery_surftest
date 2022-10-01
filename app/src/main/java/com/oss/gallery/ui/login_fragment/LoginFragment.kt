package com.oss.gallery.ui.login_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import com.oss.gallery.R
import com.oss.gallery.contract.navigator
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.databinding.FragmentLoginBinding
import com.oss.gallery.ui.base_fragments.BaseAuthFragments
import com.oss.gallery.ui.states.auth_activity_states.AuthUiStates
import com.oss.gallery.ui.states.auth_activity_states.ValidationState
import com.oss.gallery.utils.collectOnLifecycle
import com.oss.gallery.utils.setErrorStateForTextInputLayout
import com.oss.gallery.utils.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginFragment : BaseAuthFragments(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModelImpl by viewModels()

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
            viewModel.validateLoginForm(getNetworkAuthRequestModel())
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
                    navigator().changeActivity(null)
                }
                is AuthUiStates.Error<*> -> {
                    navigator().fragmentIsClickable(enable = true)
                    loginBtn.loading = false
                    //TODO classify the Network Error or Login/Password Error
                }
                is AuthUiStates.Empty -> Unit
                is AuthUiStates.Loading -> navigator().fragmentIsClickable(enable = false)
            }
        }

        viewModel.loginValidationFlow.collectOnLifecycle(this@LoginFragment) { validationState ->
            when (validationState) {
                is ValidationState.Initial -> Unit
                is ValidationState.EmptyFiledError -> {
                    loginBtn.loading = false
                    loginInputLayout.setErrorStateForTextInputLayout(
                        validationState.message
                    )
                }

                is ValidationState.IncorrectFiledError -> {
                    loginBtn.loading = false
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
                    loginBtn.loading = false
                    passwordInputLayout.setErrorStateForTextInputLayout(
                        validationState.message
                    )
                }
                is ValidationState.IncorrectFiledError -> {
                    loginBtn.loading = false
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

        viewModel.commonValidationFlow.collectOnLifecycle(this@LoginFragment) {
            if (it) viewModel.login(getNetworkAuthRequestModel())
            navigator().fragmentIsClickable(enable = false)

        }
    }

    private fun refreshTextInputLayout(textInputLayout: TextInputLayout) {
        textInputLayout.apply {
            error = null
            boxStrokeColor = R.color.black
        }
    }

    private fun initViews() = with(binding) {
        loginInputLayout.isHelperTextEnabled = false
        passwordInputLayout.isHelperTextEnabled = false
    }

    private fun getNetworkAuthRequestModel(): NetworkAuthRequest {
        return NetworkAuthRequest(
            with(binding) {
                loginInputLayout.prefixText.toString() + loginInputEt.text.toString()
                    .filter { it.isDigit() }
            },
            binding.passwordInputEt.text.toString()
        )
    }

    override fun onDestroy() = with(binding) {
        super.onDestroy()
        loginBtn.setOnClickListener(null)
    }
}
