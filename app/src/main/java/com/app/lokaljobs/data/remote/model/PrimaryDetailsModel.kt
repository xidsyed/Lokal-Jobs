package com.app.lokaljobs.data.remote.model

import com.google.gson.annotations.SerializedName


data class PrimaryDetailsModel(
    @SerializedName("Experience")
    val experience: String,

    @SerializedName("Fees_Charged")
    val feesCharged: String,

    @SerializedName("Job_Type")
    val jobType: String,

    @SerializedName("Place")
    val place: String,

    @SerializedName("Qualification")
    val qualification: String,

    @SerializedName("Salary")
    val salaryRange: String
)