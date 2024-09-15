package com.app.lokaljobs.data.remote.model

data class ContactPreference(
    val preference: Int,
    val preferred_call_end_time: String,
    val preferred_call_start_time: String,
    val whatsapp_link: String
)