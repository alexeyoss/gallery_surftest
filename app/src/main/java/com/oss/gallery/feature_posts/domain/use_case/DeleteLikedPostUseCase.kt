package com.oss.gallery.feature_posts.domain.use_case

import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.domain.repository.PostsRepository
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import com.oss.gallery.feature_posts.utils.UseCaseWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteLikedPostUseCase
@Inject
constructor(
    private val postsRepository: PostsRepository,
    private val getFavoritesPosts: GetFavoritesPostsUseCase
) : UseCaseWrapper<BasePictureCachedEntity, MainUiStates> {

    override suspend operator fun invoke(data: BasePictureCachedEntity?): Flow<MainUiStates> {
        postsRepository.unlikePostWithTimeStamp(data!!)
        return getFavoritesPosts()
    }
}

