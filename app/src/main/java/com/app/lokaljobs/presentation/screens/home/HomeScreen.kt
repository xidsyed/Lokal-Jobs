package com.app.lokaljobs.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.app.lokaljobs.data.local.JobEntity
import com.app.lokaljobs.presentation.common.JobCardList

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    jobItems: LazyPagingItems<JobEntity>,
    bookmarkedJobs: List<JobEntity>,
    onNavigateToDetails: (JobEntity) -> Unit,
    onBookmarkClick: (JobEntity) -> Unit
) {
    Column(modifier) {
        JobCardList(
            jobItems = jobItems,
            isJobCardHighlighted = false,
            bookmarkedJobs = bookmarkedJobs,
            onNavigateToDetails = onNavigateToDetails,
            onBookmarkClick = onBookmarkClick
        )
    }
}