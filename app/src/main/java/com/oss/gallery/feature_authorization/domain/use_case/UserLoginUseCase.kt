package com.oss.gallery.feature_authorization.domain.use_case

import com.oss.gallery.feature_authorization.data.network.request.NetworkAuthRequest
import com.oss.gallery.feature_authorization.domain.repository.AuthRepository
import com.oss.gallery.feature_authorization.domain.util.buildAuthRequestFlow
import com.oss.gallery.feature_authorization.presentation.states.AuthUiStates
import com.oss.gallery.feature_posts.utils.UseCaseWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLoginUseCase
@Inject
constructor(
    private val repository: AuthRepository
) : UseCaseWrapper<NetworkAuthRequest, AuthUiStates> {

    override suspend operator fun invoke(data: NetworkAuthRequest?): Flow<AuthUiStates> {
        return buildAuthRequestFlow {
            repository.login(data!!)
        }
    }
}