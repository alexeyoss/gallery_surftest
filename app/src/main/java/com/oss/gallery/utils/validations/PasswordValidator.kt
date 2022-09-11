package com.oss.gallery.utils.validations

import com.oss.gallery.R
import com.oss.gallery.ui.states.ValidationState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@Singleton
class PasswordValidator : BaseValidator<String>() {

    override suspend fun validate(data: String): Flow<ValidationState> = flow {
        if (data.isBlank())
            emit(
                ValidationState.EmptyFiledError(R.string.empty_field_error.toString())
            )
        if (data.length !in 6..255)
            emit(
                ValidationState.IncorrectFiledError(R.string.incorrect_password_length.toString())
            )
        if (data.length in 6..255 && data.isNotBlank())
            emit(ValidationState.Successful)
    }
}