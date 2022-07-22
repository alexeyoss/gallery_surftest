package com.oss.gallery.utils

import com.oss.gallery.data.entites.BasePictureModel

interface MainFragmentOnClickListener : BaseOnClickListener {
    fun onViewClicked(data: BasePictureModel)
}