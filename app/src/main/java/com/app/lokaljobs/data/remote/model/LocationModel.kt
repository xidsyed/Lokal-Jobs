package com.app.lokaljobs.data.remote.model

import com.google.gson.annotations.SerializedName


data class LocationModel(
    @SerializedName("id")
    val locationId: Int,

    @SerializedName("locale")
    val localeName: String,

    @SerializedName("state")
    val stateId: Int
)
