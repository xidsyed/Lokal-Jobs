package com.app.lokaljobs.presentation.screens.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.presentation.common.BookmarkJobCardList
import com.app.lokaljobs.presentation.common.FilterRow
import com.app.lokaljobs.presentation.getDummyJob

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    jobList: List<JobEntity>,
    onNavigateToDetails: (JobEntity) -> Unit,
    onBookmarkClick: (JobEntity) -> Unit
) {
    Column(modifier = modifier) {
        FilterRow(listOf("Created", "Bookmarked"), 0)
        BookmarkJobCardList(
            jobList = jobList,
            isJobCardHighlighted = true,
            onNavigateToDetails,
            onBookmarkClick = onBookmarkClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    BookmarkScreen(
        jobList = listOf(getDummyJob(), getDummyJob(), getDummyJob(), getDummyJob()),
        onNavigateToDetails = {},
        onBookmarkClick = {}
    )
}