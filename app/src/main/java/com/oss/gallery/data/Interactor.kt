package com.oss.gallery.data

import com.oss.gallery.network.ApiService

class Interactor
constructor(
    private val apiService: ApiService
) {

    suspend fun doSomething() {}
}