package com.oss.gallery.feature_authorization.domain.use_case

import javax.inject.Inject

data class AuthUseCases
@Inject
constructor(
    val login: UserLoginUseCase,
    val checkTokenStatus: CheckTokenStatusUseCase,
    val getTokenFromStorage: GetTokenFromStorageUseCase,
    val saveTokenIntoStorage: SaveTokenIntoStorageUseCase
)
