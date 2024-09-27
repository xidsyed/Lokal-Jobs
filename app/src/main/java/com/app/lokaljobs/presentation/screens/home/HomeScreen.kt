package com.app.lokaljobs.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.presentation.common.JobCardList
import com.app.lokaljobs.presentation.common.PullToRefreshIndicator
import com.app.lokaljobs.presentation.common.RefreshIndicatorState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    jobItems: LazyPagingItems<JobEntity>,
    bookmarkedJobs: List<JobEntity>,
    onNavigateToDetails: (JobEntity) -> Unit,
    onBookmarkClick: (JobEntity) -> Unit,
) {

    var isRefreshing by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(jobItems.loadState) {
        val _isRefreshing = jobItems.loadState.refresh is LoadState.Loading
        if (isRefreshing && !_isRefreshing) {
            delay(1000)
        }
        isRefreshing = _isRefreshing
    }

    val state = rememberPullToRefreshState()
    val onRefresh = remember { { jobItems.refresh() } }

    val lastRefreshedAt = rememberSaveable(jobItems.loadState.refresh) {
        derivedStateOf {
            if (jobItems.loadState.refresh is LoadState.NotLoading) System.nanoTime() else -1L
        }.value
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullToRefresh(
                isRefreshing = isRefreshing, state = state, onRefresh = onRefresh
            )
    ) {
        val scope = rememberCoroutineScope()
        val indicatorState by remember {
            derivedStateOf {
                return@derivedStateOf getIndicatorState(scope, state, isRefreshing)
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            PullToRefreshIndicator(
                lastRefreshedAt = lastRefreshedAt,
                refreshIndicatorState = indicatorState,
                pullToRefreshProgress = state.distanceFraction
            )
            JobCardList(
                jobItems = jobItems,
                isJobCardHighlighted = false,
                bookmarkedJobs = bookmarkedJobs,
                onNavigateToDetails = onNavigateToDetails,
                onBookmarkClick = onBookmarkClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun getIndicatorState(
    scope: CoroutineScope,
    state: PullToRefreshState,
    isRefreshing: Boolean
): RefreshIndicatorState {
    return when {
        (isRefreshing) -> {
            if (!state.isAnimating) {
                scope.launch { state.animateToThreshold() }
            }
            RefreshIndicatorState.Refreshing
        }

        (!state.isAnimating && state.distanceFraction > 1f) -> {
            // Before Letting Go of Indicator beyond threshold
            RefreshIndicatorState.ReachedThreshold
        }

        (state.isAnimating && state.distanceFraction >= 1f || !state.isAnimating && state.distanceFraction == 1f) -> {
            // After Letting Go of Indicator beyond and at the threshold
            RefreshIndicatorState.Refreshing
        }

        (!state.isAnimating && state.distanceFraction > 0f && state.distanceFraction < 1f) -> {
            // Before Letting Go, Before the threshold
            RefreshIndicatorState.PullingDown
        }

        (state.distanceFraction < 1f && state.distanceFraction > 0f) -> {
            // After Refresh to Collapse State
            if (!state.isAnimating) {
                scope.launch { state.animateToHidden() }
            }
            RefreshIndicatorState.Default
        }

        else -> {
            RefreshIndicatorState.Default
        }
    }
}
