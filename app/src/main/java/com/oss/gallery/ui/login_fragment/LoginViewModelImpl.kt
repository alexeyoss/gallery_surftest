package com.oss.gallery.ui.login_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oss.gallery.data.network.request.NetworkAuthRequest
import com.oss.gallery.di.IoDispatcher
import com.oss.gallery.ui.interactors.AuthInteractorImpl
import com.oss.gallery.ui.states.AuthUiEvents
import com.oss.gallery.ui.states.AuthUiStates
import com.oss.gallery.ui.states.ValidationState
import com.oss.gallery.utils.validations.LoginValidator
import com.oss.gallery.utils.validations.PasswordValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl
@Inject
constructor(
    private val interactor: AuthInteractorImpl,
    private val loginValidator: LoginValidator,
    private val passwordValidator: PasswordValidator,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel(), LoginViewModel {

    override val authUiStateFlow = MutableStateFlow<AuthUiStates>(AuthUiStates.Empty)
    override val authUiEventFlow =
        MutableStateFlow<AuthUiEvents>(AuthUiEvents.Login) // TODO implement
    override val loginValidationFlow =
        MutableStateFlow<ValidationState>(ValidationState.Initial)
    override val passwordValidationFlow =
        MutableStateFlow<ValidationState>(ValidationState.Initial)

    override val commonValidationFlow = MutableSharedFlow<Boolean>()

    override fun login(authRequest: NetworkAuthRequest) {
        viewModelScope.launch(ioDispatcher) {
            interactor.login(authRequest)
                .onEach { authUiStateFlow.emit(it) }
                .launchIn(viewModelScope)
        }
    }

    override fun validateLoginForm(dataModel: NetworkAuthRequest) {
        viewModelScope.launch(ioDispatcher) {

            loginValidator.validate(dataModel.phone)
                .onEach { loginValidationFlow.emit(it) }
                .launchIn(viewModelScope)

            passwordValidator.validate(dataModel.password)
                .onEach { passwordValidationFlow.emit(it) }
                .launchIn(viewModelScope)


            loginValidationFlow.zip(passwordValidationFlow) { login, pass ->
                Pair(login, pass)
            }.collect {
                if (it.first is ValidationState.Successful && it.second is ValidationState.Successful) {
                    commonValidationFlow.emit(true)
                }
            }
        }
    }
}