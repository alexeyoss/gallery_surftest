package com.oss.gallery.data

import com.oss.gallery.data.entites.BasePictureModelMapper
import com.oss.gallery.network.ApiService

class Interactor
constructor(
    private val apiService: ApiService,
    private val baseModelMapper: BasePictureModelMapper
) {

    suspend fun doSomething() {}
}