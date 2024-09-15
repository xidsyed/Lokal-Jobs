package com.app.lokaljobs.data.remote.model

import com.google.gson.annotations.SerializedName

data class ContactPreferenceModel(
    @SerializedName("preference")
    val callPreference: Int,

    @SerializedName("preferred_call_end_time")
    val callEndTime: String,

    @SerializedName("preferred_call_start_time")
    val callStartTime: String,

    @SerializedName("whatsapp_link")
    val whatsappLink: String
)
