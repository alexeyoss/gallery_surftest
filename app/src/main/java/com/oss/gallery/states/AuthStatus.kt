package com.oss.gallery.states

sealed class AuthStatus<out R, T> {
    data class Authorized(val statusCode: Int, val reason: String) : AuthStatus<Int, String>()
    data class Unauthorized(val statusCode: Int, val reason: String) : AuthStatus<Int, String>()
}
