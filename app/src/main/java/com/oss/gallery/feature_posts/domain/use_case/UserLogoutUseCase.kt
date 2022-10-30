package com.oss.gallery.feature_posts.domain.use_case

import com.oss.gallery.feature_posts.domain.repository.MainRepository
import com.oss.gallery.feature_posts.domain.util.buildMainRequestFlow
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import com.oss.gallery.feature_posts.utils.UseCaseWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLogoutUseCase
@Inject
constructor(
    private val repository: MainRepository
) : UseCaseWrapper<Nothing, MainUiStates> {

    override suspend operator fun invoke(data: Nothing?): Flow<MainUiStates> {
        return buildMainRequestFlow {
            repository.logout()
        }
    }
}