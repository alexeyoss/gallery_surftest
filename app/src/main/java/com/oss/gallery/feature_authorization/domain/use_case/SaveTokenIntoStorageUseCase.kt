package com.oss.gallery.feature_authorization.domain.use_case

import com.oss.gallery.feature_authorization.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveTokenIntoStorageUseCase
@Inject
constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(token: String): Flow<Boolean> {
        return flow {
            repository.saveTokenIntoStorage(token)
        }
    }
}