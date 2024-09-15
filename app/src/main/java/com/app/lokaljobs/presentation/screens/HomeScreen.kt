package com.app.lokaljobs.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.app.lokaljobs.data.local.Job
import com.app.lokaljobs.presentation.screens.components.JobCardList

@Composable
fun HomeScreen(
    jobItems: LazyPagingItems<Job>,
    bookmarkedJobs: List<Job>,
    onNavigateToDetails: (Job) -> Unit,
    onBookmarkClick: (Job) -> Unit
) {
    Column {
        JobCardList(
            jobItems = jobItems,
            isJobCardHighlighted = false,
            bookmarkedJobs = bookmarkedJobs,
            onNavigateToDetails = onNavigateToDetails,
            onBookmarkClick = onBookmarkClick
        )
    }
}