package com.oss.gallery.feature_authorization.domain.use_case

import com.oss.gallery.feature_authorization.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTokenFromStorageUseCase
@Inject
constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Flow<String> {
        return flow {
            repository.getTokenFromStorage()
        }
    }
}