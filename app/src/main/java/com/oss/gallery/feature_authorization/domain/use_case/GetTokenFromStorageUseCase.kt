package com.oss.gallery.feature_authorization.domain.use_case

import com.oss.gallery.feature_authorization.domain.repository.AuthRepository
import com.oss.gallery.feature_posts.utils.UseCaseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTokenFromStorageUseCase
@Inject
constructor(
    private val repository: AuthRepository
) : UseCaseWrapper<Nothing, String> {

    override suspend operator fun invoke(data: Nothing?): Flow<String> {
        return flow {
            repository.getTokenFromStorage()
        }
    }
}