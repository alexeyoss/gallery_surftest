package com.oss.gallery.utils.listeners

import com.oss.gallery.data.model.BasePictureModel

interface MainFragmentOnClickListener : BaseOnClickListener {
    fun onViewClicked(data: BasePictureModel)
}