package com.oss.gallery.feature_posts.domain.use_case

import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.domain.repository.PostsRepository
import com.oss.gallery.feature_posts.presentation.states.MainUiStates
import com.oss.gallery.feature_posts.utils.UseCaseWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikePostWithTimeStampUseCase
@Inject
constructor(
    private val repository: PostsRepository,
    private val getAllCachedPosts: GetAllCachedPostsUseCase
) : UseCaseWrapper<BasePictureCachedEntity, MainUiStates> {

    override suspend operator fun invoke(data: BasePictureCachedEntity?): Flow<MainUiStates> {
        if (data!!.liked) repository.unlikePostWithTimeStamp(data)
        else repository.likePostWithTimeStamp(data)
        return getAllCachedPosts()
    }
}