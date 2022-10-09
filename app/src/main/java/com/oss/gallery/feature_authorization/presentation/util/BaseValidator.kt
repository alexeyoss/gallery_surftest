package com.oss.gallery.feature_authorization.presentation.util

import com.oss.gallery.feature_authorization.presentation.states.ValidationState
import kotlinx.coroutines.flow.Flow

abstract class BaseValidator<T> {

    abstract suspend fun validate(data: T): Flow<ValidationState>
}