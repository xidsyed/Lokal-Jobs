package com.app.lokaljobs.data.remote.model

import com.google.gson.annotations.SerializedName

data class ContentFieldModel(
    @SerializedName("field_key")
    val key: String,

    @SerializedName("field_name")
    val name: String,

    @SerializedName("field_value")
    val value: String
)
