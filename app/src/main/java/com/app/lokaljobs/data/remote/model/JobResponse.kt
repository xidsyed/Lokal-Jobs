package com.app.lokaljobs.data.remote.model

import com.google.gson.annotations.SerializedName

data class JobResponse(
    @SerializedName("results")
    val jobResults: List<JobResultModel>
)
