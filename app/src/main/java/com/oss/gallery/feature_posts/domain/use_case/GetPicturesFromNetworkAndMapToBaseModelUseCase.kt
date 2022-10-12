package com.oss.gallery.feature_posts.domain.use_case

import com.oss.gallery.feature_posts.domain.repository.MainRepository
import com.oss.gallery.feature_posts.domain.util.buildMainRequestFlow
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPicturesFromNetworkAndMapToBaseModelUseCase
@Inject
constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(): Flow<MainUiStates> {
        return buildMainRequestFlow {
            repository.getPicturesFromNetworkAndMapToBasePictureCachedModel()
        }
    }
}