package com.oss.gallery.states

sealed class AuthState<out T> {
    data class Authorized<T>(internal val data: T) : AuthState<T>()
    data class UnAuthorized<T>(internal val statusCode: T) : AuthState<T>()
}
