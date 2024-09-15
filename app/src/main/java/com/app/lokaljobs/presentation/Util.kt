package com.app.lokaljobs.presentation

import com.app.lokaljobs.data.local.Job
import com.app.lokaljobs.data.local.JobCategory
import com.app.lokaljobs.presentation.screens.components.BottomNavigationItem
import com.cinderella.lokaljobs.R
import java.text.SimpleDateFormat
import java.util.Locale

fun Job.getTime(): String? {
    return runCatching {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
        val date = simpleDateFormat.parse(createdOn) // Parse the ISO 8601 format
        val outputFormat = SimpleDateFormat("dd MMM yy", Locale.getDefault())
        date?.let { outputFormat.format(it) } ?: "Invalid date"
    }.getOrNull()
}


fun Job.getCategory(): JobCategory {
    return JobCategory.fromId(categoryId)
}

fun getDummyJob() = Job(
    id = 623051,
    title = "Faculty, High School, Primary & Pre-Primary Teachers, RROs, Marketing Executives wanted for Hms, IIT/ NEET Foundation",
    location = "Suryapet",
    minSalary = 35000,
    maxSalary = 39000,
    experience = "Less than 1 year",
    feesCharged = -1,  // As per the data
    categoryId = 50,
    openingCount = 40,
    createdOn = "2024-04-01T15:54:36.543241+05:30",
    companyName = "IIT/ NEET Foundation, high school",
    customTelLink = "tel:9885108599",
    whatsappNumber = "7995718999",
    otherDetails = """
        Wanted Faculty, High School, Primary & Pre Primary Teachers, RROs, Marketing Executives, Receptionist, Computer Faculty & Operators for Hms, IIT/ NEET Foundation, Accountant for Montessori Group of Schools, Tirumalagiri-508223, Suryapet District, Telangana.
        
        Mailid: montessorithir@gmail.com
        Salary: Based on Role & Responsibilities
        Eligibility: Post Graduation or Graduation
        Job Location: Suryapet
    """.trimIndent(),
    category = "Teaching"
)

fun getDummyBottomNavigationList() : List<BottomNavigationItem> = listOf(
    BottomNavigationItem(
        icon = R.drawable.icon_experience,
        iconSelected = R.drawable.icon_experience,
        label = "Jobs",
        destination = Route.HomeScreen
    ),
    BottomNavigationItem(
        icon = R.drawable.bookmark_dark,
        iconSelected = R.drawable.bookmark_filled_dark,
        label = "Bookmarks",
        destination = Route.BookmarkScreen
    ),
)
