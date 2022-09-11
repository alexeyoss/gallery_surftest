package com.oss.gallery.utils.validations

import com.oss.gallery.ui.states.ValidationState
import kotlinx.coroutines.flow.Flow

abstract class BaseValidator<T> {

    abstract suspend fun validate(data: T): Flow<ValidationState>
}