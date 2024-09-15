package com.app.lokaljobs.data.remote.model


import com.google.gson.annotations.SerializedName

data class JobResultModel(
    @SerializedName("advertiser")
    val advertiserId: Int,

    @SerializedName("amount")
    val salaryAmount: String,

    @SerializedName("button_text")
    val actionButtonText: String,

    @SerializedName("city_location")
    val cityLocationId: Int,

    @SerializedName("company_name")
    val companyName: String,

    @SerializedName("contact_preference")
    val contactPreference: ContactPreferenceModel,

    @SerializedName("content")
    val content: String,

    @SerializedName("contentV3")
    val contentFields: ContentModel,

    @SerializedName("created_on")
    val createdOn: String,

    @SerializedName("creatives")
    val creatives: List<CreativeModel>,

    @SerializedName("custom_link")
    val customLink: String,

    @SerializedName("enable_lead_collection")
    val isLeadCollectionEnabled: Boolean,

    @SerializedName("experience")
    val experienceInYears: Int,

    @SerializedName("expire_on")
    val expiresOn: String,

    @SerializedName("fb_shares")
    val fbShares: Int,

    @SerializedName("fee_details")
    val feeDetails: FeeDetailsModel,

    @SerializedName("fees_charged")
    val feesCharged: Int,

    @SerializedName("fees_text")
    val feesDescription: String,

    @SerializedName("id")
    val jobId: Int,

    @SerializedName("is_applied")
    val isApplied: Boolean,

    @SerializedName("is_bookmarked")
    val isBookmarked: Boolean,

    @SerializedName("is_job_seeker_profile_mandatory")
    val isJobSeekerProfileMandatory: Boolean,

    @SerializedName("is_owner")
    val isOwner: Boolean,

    @SerializedName("is_premium")
    val isPremium: Boolean,

    @SerializedName("job_category")
    val jobCategoryName: String,

    @SerializedName("job_category_id")
    val jobCategoryId: Int,

    @SerializedName("job_hours")
    val jobHours: String,

    @SerializedName("job_location_slug")
    val jobLocationSlug: String,

    @SerializedName("job_role")
    val jobRole: String,

    @SerializedName("job_role_id")
    val jobRoleId: Int,

    @SerializedName("job_tags")
    val jobTags: List<JobTagModel>,

    @SerializedName("job_type")
    val jobTypeId: Int,

    @SerializedName("locality")
    val localityId: Int,

    @SerializedName("locations")
    val locationList: List<LocationModel>,

    @SerializedName("num_applications")
    val applicationCount: Int,

    @SerializedName("openings_count")
    val openingsCount: Int,

    @SerializedName("other_details")
    val otherDetails: String,

    @SerializedName("premium_till")
    val premiumUntil: String?,

    @SerializedName("primary_details")
    val primaryDetails: PrimaryDetailsModel,

    @SerializedName("qualification")
    val qualificationId: Int,

    @SerializedName("question_bank_id")
    val questionBankId: Any?,

    @SerializedName("salary_max")
    val salaryMax: Int?,

    @SerializedName("salary_min")
    val salaryMin: Int?,

    @SerializedName("screening_retry")
    val screeningRetryCount: Any?,

    @SerializedName("shares")
    val shareCount: Int,

    @SerializedName("shift_timing")
    val shiftTiming: Int,

    @SerializedName("should_show_last_contacted")
    val showLastContacted: Boolean,

    @SerializedName("status")
    val jobStatus: Int,

    @SerializedName("tags")
    val jobTagsList: List<Any>,

    @SerializedName("title")
    val jobTitle: String,

    @SerializedName("translated_content")
    val translatedContent: TranslatedContentModel,

    @SerializedName("type")
    val jobType: Int,

    @SerializedName("updated_on")
    val updatedOn: String,

    @SerializedName("videos")
    val videos: List<Any>,

    @SerializedName("views")
    val viewCount: Int,

    @SerializedName("whatsapp_no")
    val whatsappNumber: String?
)
