package com.oss.gallery

sealed class Events<out T> {
    data class Login<T>(internal val login: String, internal val password: String) : Events<T>()
    object Logout : Events<Nothing>()
    data class GetPictures<T>(internal val token: String) : Events<T>()
}
