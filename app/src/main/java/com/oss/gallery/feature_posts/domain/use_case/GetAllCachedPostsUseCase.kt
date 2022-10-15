package com.oss.gallery.feature_posts.domain.use_case

import com.oss.gallery.feature_posts.domain.repository.PostsRepository
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCachedPostsUseCase
@Inject
constructor(
    private val repositoryImpl: PostsRepository
) {
    suspend operator fun invoke(): Flow<MainUiStates> =
        flow {
            emit(MainUiStates.Loading)
            val result = repositoryImpl.getAllCachedPostsFromDb()
            if (result.isNotEmpty())
                emit(MainUiStates.Success(result))
            else if (result.isEmpty()) emit(MainUiStates.Empty)
            else emit(MainUiStates.Error(result))
        }
}