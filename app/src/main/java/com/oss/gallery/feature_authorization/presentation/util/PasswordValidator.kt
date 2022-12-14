package com.oss.gallery.feature_authorization.presentation.util

import com.oss.gallery.R
import com.oss.gallery.feature_authorization.presentation.states.ValidationState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PasswordValidator : BaseValidator<String>() {

    override suspend fun validate(data: String): Flow<ValidationState> = flow {
        if (data.isEmpty())
            emit(
                ValidationState.EmptyFiledError(R.string.empty_field_error)
            )
        else if (data.length !in 6..255)
            emit(
                ValidationState.IncorrectFiledError(R.string.incorrect_password_length)
            )
        else if (data.length in 6..255 && data.isNotBlank())
            emit(ValidationState.Successful)
    }
}