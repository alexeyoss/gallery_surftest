package com.oss.gallery.feature_authorization.domain.use_case

import com.oss.gallery.feature_authorization.domain.repository.AuthRepository
import com.oss.gallery.feature_posts.utils.UseCaseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveTokenIntoStorageUseCase
@Inject
constructor(
    private val repository: AuthRepository
) : UseCaseWrapper<String, Boolean> {

    override suspend operator fun invoke(data: String?): Flow<Boolean> {
        return flow {
            repository.saveTokenIntoStorage(data!!)
        }
    }
}