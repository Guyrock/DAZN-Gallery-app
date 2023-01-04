package com.dazn.gallery.models

import java.io.Serializable

data class ImgDetail(
    val url:String?,
    val title:String,
    val explanation:String,
    val date:String,
    val hdurl:String?
)
