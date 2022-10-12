package com.oss.gallery.feature_posts.domain.use_case

import com.oss.gallery.feature_posts.data.database.entities.BasePictureCachedEntity
import com.oss.gallery.feature_posts.domain.repository.PostsRepository
import javax.inject.Inject

class LikePostWithTimeStampUseCase
@Inject
constructor(
    private val repository: PostsRepository
) {
    suspend operator fun invoke(post: BasePictureCachedEntity) {
        repository.likePostWithTimeStamp(post)
    }
}