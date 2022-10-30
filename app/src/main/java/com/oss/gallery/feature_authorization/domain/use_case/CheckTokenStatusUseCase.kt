package com.oss.gallery.feature_authorization.domain.use_case

import com.oss.gallery.feature_authorization.domain.repository.AuthRepository
import com.oss.gallery.feature_authorization.domain.util.buildAuthRequestFlow
import com.oss.gallery.feature_authorization.presentation.states.AuthUiStates
import com.oss.gallery.feature_posts.utils.UseCaseWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckTokenStatusUseCase
@Inject
constructor(
    private val repository: AuthRepository
) : UseCaseWrapper<Nothing, AuthUiStates> {

    override suspend operator fun invoke(data: Nothing?): Flow<AuthUiStates> {
        return buildAuthRequestFlow {
            repository.checkTokenState()
        }
    }
}
