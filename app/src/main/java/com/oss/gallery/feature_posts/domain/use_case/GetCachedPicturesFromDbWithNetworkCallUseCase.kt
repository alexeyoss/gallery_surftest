package com.oss.gallery.feature_posts.domain.use_case

import com.oss.gallery.feature_posts.data.network.request.NetworkRequestState
import com.oss.gallery.feature_posts.domain.repository.MainRepository
import com.oss.gallery.feature_posts.domain.repository.PostsRepository
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import com.oss.gallery.feature_posts.utils.UseCaseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCachedPicturesFromDbWithNetworkCallUseCase
@Inject
constructor(
    private val mainRepository: MainRepository,
    private val postsRepository: PostsRepository
) : UseCaseWrapper<Nothing, MainUiStates> {

    override suspend operator fun invoke(data: Nothing?): Flow<MainUiStates> {
        return flow {
            emit(MainUiStates.Loading)
            val result = when (val request =
                mainRepository.getPicturesFromNetworkAndMapToBasePictureCachedModel()) {
                is NetworkRequestState.Success -> {
                    // TODO Test the logic
                    if (!postsRepository.saveAllUniqueData(request.data)) {
                        MainUiStates.Error(request)
                    } else {
                        MainUiStates.Success(postsRepository.getAllCachedPostsFromDb())
                    }
                }
                else -> MainUiStates.Error(request)
            }
            emit(result)
        }
    }
}
