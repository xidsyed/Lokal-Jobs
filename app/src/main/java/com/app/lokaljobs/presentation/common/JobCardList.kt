package com.app.lokaljobs.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.presentation.screens.EmptyScreen
import com.app.lokaljobs.ui.theme.LightGray

@Composable
fun JobCardList(
    modifier: Modifier = Modifier,
    jobItems: LazyPagingItems<JobEntity>,
    bookmarkedJobs: List<JobEntity>,
    isJobCardHighlighted: Boolean,
    onNavigateToDetails: (JobEntity) -> Unit,
    onBookmarkClick: (JobEntity) -> Unit
) {

    val pagingResultReceived = handlePagingResult(jobItems)
    if (!pagingResultReceived) return

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(jobItems.itemCount) { index ->
            jobItems[index]?.let { job ->
                JobCard(
                    job = job,
                    isBookmarked = bookmarkedJobs.any { it.id == job.id },
                    isHighlighted = isJobCardHighlighted,
                    onJobCardClick = onNavigateToDetails,
                    onBookmarkIconClick = onBookmarkClick
                )
            }

        }
    }

}

@Composable
private fun handlePagingResult(jobItems: LazyPagingItems<JobEntity>): Boolean {
    val loadState = jobItems.loadState

    // get error if any of the load states has an error
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = LightGray,
                    strokeWidth = 3.dp
                )
            }
            false
        }

        error != null -> {
            EmptyScreen(message = "Error Fetching From Server. Try again.")
            false
        }

        jobItems.itemCount == 0 -> {
            EmptyScreen(message = "No Jobs Found")
            false
        }

        else -> true
    }
}
