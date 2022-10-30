package com.oss.gallery.feature_posts.utils

import kotlinx.coroutines.flow.Flow

interface UseCaseWrapper<DataClass, State> {
    suspend operator fun invoke(data: DataClass? = null): Flow<State>
}
