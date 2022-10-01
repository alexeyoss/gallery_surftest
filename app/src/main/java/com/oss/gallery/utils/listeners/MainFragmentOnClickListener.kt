package com.oss.gallery.utils.listeners

import com.oss.gallery.data.network.response.NetworkPictureResponse

interface MainFragmentOnClickListener : BaseOnClickListener, LikeOnClickListener {
    fun onViewClicked(data: NetworkPictureResponse)
}