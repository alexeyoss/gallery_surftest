package com.oss.gallery.ui.states

sealed interface TokenState {
    data class Exist(internal val token: String) : TokenState
    object Empty : TokenState
}