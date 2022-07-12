package com.oss.gallery.states

sealed class AuthState<out R, T> {
    data class Authorized(val statusCode: Int, val reason: String) : AuthState<Int, String>()
    data class Unauthorized(val statusCode: Int, val reason: String) : AuthState<Int, String>()
}
