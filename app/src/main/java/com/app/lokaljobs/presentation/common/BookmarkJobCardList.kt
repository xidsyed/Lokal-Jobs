package com.app.lokaljobs.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.app.lokaljobs.data.local.JobEntity
import com.app.lokaljobs.presentation.screens.EmptyScreen

@Composable
fun BookmarkJobCardList(
    jobList: List<JobEntity>,
    isJobCardHighlighted: Boolean,
    onNavigateToDetails: (JobEntity) -> Unit,
    onBookmarkClick: (JobEntity) -> Unit
) {

    val isEmpty = if (jobList.isEmpty()) {
        EmptyScreen(message = "No Bookmarks")
        false
    } else true

    if (!isEmpty) return

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(jobList.size) { index ->
            val job = jobList[index]
            JobCard(
                job = job,
                isBookmarked = true,
                isHighlighted = isJobCardHighlighted,
                onJobCardClick = onNavigateToDetails,
                onBookmarkIconClick = onBookmarkClick
            )
        }
    }
}
