package com.oss.gallery.utils.validations

import com.oss.gallery.R
import com.oss.gallery.ui.states.auth_activity_states.ValidationState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@Singleton
class LoginValidator : BaseValidator<String>() {

    override suspend fun validate(data: String): Flow<ValidationState> = flow {
        if (data.isBlank())
            emit(
                ValidationState.EmptyFiledError(R.string.empty_field_error) // TODO add annotation @StringRes or maybe DataModel not hte raw text
            )
        if (!regexp.matches(data))
            emit(
                ValidationState.IncorrectFiledError(R.string.incorrect_phone_format)
            )
        if (data.isNotBlank() && regexp.matches(data))
            emit(ValidationState.Successful)
    }

    companion object {
        @JvmStatic
        val regexp = "\\+7\\d{10}\$".toRegex()
    }
}