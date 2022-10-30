package com.oss.gallery.feature_posts.domain.use_case

import com.oss.gallery.feature_posts.domain.repository.PostsRepository
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import com.oss.gallery.feature_posts.utils.UseCaseWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesPostsUseCase
@Inject
constructor(
    private val postsRepository: PostsRepository
) : UseCaseWrapper<Nothing, MainUiStates> {

    override suspend operator fun invoke(data: Nothing?): Flow<MainUiStates> =
        flow {
            emit(MainUiStates.Loading)
            val result = postsRepository.getFavoritesPosts()
            if (result.isNotEmpty()) emit(MainUiStates.Success(result))
            if (result.isEmpty()) emit(MainUiStates.Empty)
            else emit(MainUiStates.Error(result))
        }
}