package com.app.lokaljobs.data.local

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.lokaljobs.data.remote.model.JobsDto
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Job(
    @PrimaryKey
    val id : Int,
    val title: String,
    val location: String,
    val minSalary: Int?,
    val maxSalary: Int?,
    val experience: String,
    val feesCharged: Int?,
    val categoryId: Int,
    val category: String,
    val openingCount: Int,
    val createdOn: String,
    val companyName: String,
    val customTelLink: String,
    val whatsappNumber: String?,
    val otherDetails: String?
) : Parcelable {
    override fun toString(): String {
        return buildString {
            appendLine("Job ID: $id")
            appendLine("Job Details:")
            appendLine("Title: $title")
            appendLine("Place: $location")
            appendLine(if (minSalary != null && maxSalary != null) "Salary Range: $minSalary - $maxSalary" else "Not specified")
            appendLine("Experience: $experience")
            appendLine("Fees Charged: ${feesCharged ?: "Not specified"}")
            appendLine("Job Category: $categoryId")
            appendLine("Opening Count: $openingCount")
            appendLine("Created On: $createdOn")
            appendLine("Company Name: $companyName")
            appendLine("Custom Tel Link: $customTelLink")
            appendLine("WhatsApp Number: ${whatsappNumber?.let { "+91 $it" } ?: "Not specified"}")
            appendLine("Other Details: $otherDetails")
        }
    }
}

fun JobsDto.toJobs(): List<Job> = results.mapNotNull { result ->
    runCatching {
        result.run {
            Job(
                id = id,
                title = title,
                categoryId = job_category_id,
                category = job_category,
                location = job_location_slug,
                minSalary = salary_min,
                maxSalary = salary_max,
                experience = primary_details.Experience,
                feesCharged = primary_details.Fees_Charged.let {
                    if (it == "-1") null else it.toInt()
                },
                openingCount = openings_count,
                createdOn = created_on,
                companyName = company_name,
                customTelLink = custom_link,
                whatsappNumber = whatsapp_no,
                otherDetails = other_details,
            )
        }
    }.getOrNull()
}

// TODO : Implement the icon
enum class JobCategory(val id: Int, val title: String, @DrawableRes val iconResId: Int? = null) {
    ADMIN_BACK_OFFICE(379, "Admin / Back Office"),
    TELECALLING(14, "Telecalling / BPO"),
    HOSPITALITY_AND_TRAVEL(4, "Hospitality & Travel"),
    MEDICAL_DOCTOR(20, "Medical / Doctor"),
    TEACHING(50, "Teaching"),
    UNKNOWN(-1, "Unknown");

    companion object {
        private val map: Map<Int, JobCategory> = values().associateBy(JobCategory::id)
        fun fromId(id: Int): JobCategory = map[id] ?: UNKNOWN
    }
}
