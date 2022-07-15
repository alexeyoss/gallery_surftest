package com.oss.gallery

sealed class Events<T> {
    data class Login<T>(internal val login: String, internal val password: String) : Events<T>()
    data class GetPictures<T>(internal val token: String) : Events<T>()
}
