package com.app.lokaljobs.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.presentation.screens.EmptyScreen
import com.app.lokaljobs.ui.theme.LightGray
import kotlinx.coroutines.launch

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

    val lazyListState = rememberLazyListState()
    val showScrollToTop by remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 1 } }
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            state = lazyListState,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(jobItems.itemCount, key = { jobItems[it]?.id ?: it }) { index ->
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
            if (jobItems.loadState.append is LoadState.Loading) {
                item { LoadingSpinner(size = 32.dp, strokeWidth = 2.dp, color = LightGray) }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 36.dp, vertical = 28.dp),
        ) {
            val scope = rememberCoroutineScope()
            ScrollToTopButton(
                isVisible = showScrollToTop,
                onClick = { scope.launch { lazyListState.animateScrollToItem(0) } }
            )
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
            LoadingSpinner(
                size = 64.dp,
                strokeWidth = 3.dp,
                color = LightGray
            )
            false
        }

        error != null -> {
            if (jobItems.itemCount == 0) {
                EmptyScreen(message = "Error Fetching From Server. Try again.")
                false
            } else true
        }

        jobItems.itemCount == 0 -> {
            EmptyScreen(message = "No Jobs Found")
            false
        }

        else -> true
    }
}
