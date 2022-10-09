package com.oss.gallery.feature_authorization.presentation.util

import com.oss.gallery.R
import com.oss.gallery.feature_authorization.presentation.states.ValidationState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginValidator : BaseValidator<String>() {

    override suspend fun validate(data: String): Flow<ValidationState> = flow {
        if (data.replace("+7", "").isEmpty())
            emit(
                ValidationState.EmptyFiledError(R.string.empty_field_error)
            )
        else if (!regexp.matches(data) || data.length < 12)
            emit(
                ValidationState.IncorrectFiledError(R.string.incorrect_phone_format)
            )
        else if (data.isNotEmpty() && regexp.matches(data))
            emit(ValidationState.Successful)
    }

    companion object {
        @JvmStatic
        val regexp = "\\+7\\d{10}\$".toRegex()
    }
}