package com.app.lokaljobs.presentation

import androidx.compose.runtime.Composable
import com.app.lokaljobs.data.local.model.JobCategory
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.presentation.common.BookmarkJobCardList
import com.app.lokaljobs.presentation.common.BottomNavigationItem
import com.app.lokaljobs.presentation.navigation.Route
import com.cinderella.lokaljobs.R
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit.DAYS
import java.util.concurrent.TimeUnit.HOURS
import java.util.concurrent.TimeUnit.MINUTES
import java.util.concurrent.TimeUnit.NANOSECONDS
import java.util.concurrent.TimeUnit.SECONDS

fun JobEntity.getTime(): String? {
    return runCatching {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
        val date = simpleDateFormat.parse(createdOn)
        val outputFormat = SimpleDateFormat("dd MMM yy", Locale.getDefault())
        date?.let { outputFormat.format(it) } ?: "Invalid date"
    }.getOrNull()
}

fun formatElapsedTime(startTimeNanos: Long, endTimeNanos: Long): String {
    val tNanos = endTimeNanos - startTimeNanos
    val tSeconds = NANOSECONDS.toSeconds(tNanos)

    return when {
        tSeconds < MINUTES.toSeconds(1) -> "$tSeconds seconds"
        tSeconds < HOURS.toSeconds(1) -> "${SECONDS.toMinutes(tSeconds)} minutes"
        tSeconds < DAYS.toSeconds(1) -> "${SECONDS.toHours(tSeconds)} hours"
        else -> "${SECONDS.toDays(tSeconds)} days"
    }

}

fun JobEntity.getCategory(): JobCategory {
    return JobCategory.fromId(categoryId)
}

fun getDummyJob() = JobEntity(
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

fun getDummyBottomNavigationList(): List<BottomNavigationItem> = listOf(
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

@Composable
fun DummyBookmarkJobCardList() {
    BookmarkJobCardList(
        jobList = listOf(
            getDummyJob(),
            getDummyJob(),
            getDummyJob(),
            getDummyJob(),
            getDummyJob(),
            getDummyJob(),
            getDummyJob(),
            getDummyJob(),
            getDummyJob(),
        ),
        onBookmarkClick = {},
        onNavigateToDetails = {},
        isJobCardHighlighted = false,
    )

}