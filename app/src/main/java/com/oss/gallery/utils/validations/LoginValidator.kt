package com.oss.gallery.utils.validations

import com.oss.gallery.R
import com.oss.gallery.ui.states.ValidationState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@Singleton
class LoginValidator : BaseValidator<String>() {

    override suspend fun validate(data: String): Flow<ValidationState> = flow {
        if (data.isBlank())
            emit(
                ValidationState.EmptyFiledError(R.string.empty_field_error.toString())
            )
        if (!regexp.matches(data))
            emit(
                ValidationState.IncorrectFiledError(R.string.incorrect_phone_format.toString())
            )
        if (data.isNotBlank() && regexp.matches(data))
            emit(ValidationState.Successful)
    }

    companion object {
        @JvmStatic
        val regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$".toRegex()
    }
}