package com.oss.gallery.ui.activities

import com.oss.gallery.data.network.request.NetworkAuthRequest

interface AuthViewModel {

    fun login(authRequest: NetworkAuthRequest)
}