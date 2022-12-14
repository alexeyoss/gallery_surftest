package com.oss.gallery.feature_authorization.presentation.login_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.di.CoroutinesModule
import com.oss.gallery.feature_authorization.data.network.request.NetworkAuthRequest
import com.oss.gallery.feature_authorization.domain.use_case.AuthUseCases
import com.oss.gallery.feature_authorization.presentation.states.AuthUiEvents
import com.oss.gallery.feature_authorization.presentation.states.AuthUiEvents.Login
import com.oss.gallery.feature_authorization.presentation.states.AuthUiStates
import com.oss.gallery.feature_authorization.presentation.states.ValidationState
import com.oss.gallery.feature_authorization.presentation.util.LoginValidator
import com.oss.gallery.feature_authorization.presentation.util.PasswordValidator
import com.oss.gallery.feature_posts.utils.launchWithIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl
@Inject
constructor(
    private val authUseCases: AuthUseCases,
    private val loginValidator: LoginValidator,
    private val passwordValidator: PasswordValidator,
    @CoroutinesModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
//    @Assisted private val savedState: SavedStateHandle // TODO implement
) : ViewModel(), LoginViewModel {

    private val _authUiStateFlow = MutableStateFlow<AuthUiStates>(AuthUiStates.Empty)
    override val authUiStateFlow = _authUiStateFlow.asStateFlow()

    private val _authUiEventFlow =
        MutableStateFlow<AuthUiEvents>(Login)
    override val authUiEventFlow = _authUiEventFlow.asStateFlow()

    private val _loginValidationFlow =
        MutableStateFlow<ValidationState>(ValidationState.Initial)
    override val loginValidationFlow = _loginValidationFlow.asStateFlow()

    private val _passwordValidationFlow =
        MutableStateFlow<ValidationState>(ValidationState.Initial)
    override val passwordValidationFlow = _passwordValidationFlow.asStateFlow()

    private val _commonValidationFlow = MutableSharedFlow<Boolean>()
    override val commonValidationFlow = _commonValidationFlow.asSharedFlow()

    override fun login(authRequest: NetworkAuthRequest) {
        launchWithIO(
            useCase = { authUseCases.login(authRequest) },
            stateFlow = _authUiStateFlow,
            eventFlow = _authUiEventFlow,
            event = Login
        )
    }

    override fun validateLoginForm(dataModel: NetworkAuthRequest) {
        viewModelScope.launch(ioDispatcher) {

            loginValidator.validate(dataModel.phone)
                .onEach { _loginValidationFlow.emit(it) }
                .launchIn(viewModelScope)

            passwordValidator.validate(dataModel.password)
                .onEach { _passwordValidationFlow.emit(it) }
                .launchIn(viewModelScope)


            _loginValidationFlow.zip(_passwordValidationFlow) { login, pass ->
                Pair(login, pass)
            }.collect {
                if (it.first is ValidationState.Successful && it.second is ValidationState.Successful) {
                    _commonValidationFlow.emit(true)
                } else {
                    _commonValidationFlow.emit(false)
                }
            }
        }
    }
}