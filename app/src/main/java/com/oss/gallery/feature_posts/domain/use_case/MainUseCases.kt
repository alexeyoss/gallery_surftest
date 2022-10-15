package com.oss.gallery.feature_posts.domain.use_case

import javax.inject.Inject

data class MainUseCases
@Inject
constructor(
    val logout: UserLogoutUseCase,
    val cleanStorageResources: CleanStorageResourcesUseCase,
    val getCachedPicturesFromDbWithNetworkCallUseCase: GetCachedPicturesFromDbWithNetworkCallUseCase,
    val onLikeClicked: LikePostWithTimeStampUseCase,
    val getAllCachedPosts: GetAllCachedPostsUseCase
)