package com.app.lokaljobs.data.remote.model

import com.google.gson.annotations.SerializedName

data class CreativeModel(
    @SerializedName("creative_type")
    val creativeType: Int,

    @SerializedName("file")
    val fileUrl: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("order_id")
    val orderId: Int,

    @SerializedName("thumb_url")
    val thumbnailUrl: String
)
