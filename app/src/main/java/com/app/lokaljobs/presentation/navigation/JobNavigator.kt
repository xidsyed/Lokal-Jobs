package com.app.lokaljobs.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.presentation.common.BottomNavigation
import com.app.lokaljobs.presentation.common.BottomNavigationItem
import com.app.lokaljobs.presentation.common.GenericSnackBarHost
import com.app.lokaljobs.presentation.common.GenericSnackbarDuration
import com.app.lokaljobs.presentation.common.GenericSnackbarHostState
import com.app.lokaljobs.presentation.common.NavigatorTopBar
import com.app.lokaljobs.presentation.common.SmartSnackbar
import com.app.lokaljobs.presentation.common.SnackbarMessage
import com.app.lokaljobs.presentation.screens.bookmark.BookmarkScreen
import com.app.lokaljobs.presentation.screens.details.DetailScreen
import com.app.lokaljobs.presentation.screens.home.HomeScreen
import com.app.lokaljobs.ui.theme.Gray
import com.cinderella.lokaljobs.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobNavigator(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState().value

    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            icon = R.drawable.icon_experience,
            iconSelected = R.drawable.icon_experience,
            label = "Jobs",
            destination = Route.HomeScreen
        ),
        BottomNavigationItem(
            icon = R.drawable.bookmark_unselected,
            iconSelected = R.drawable.bookmark_filled_dark,
            label = "Bookmarks",
            destination = Route.BookmarkScreen
        ),
    )

    var selectedState by rememberSaveable { mutableIntStateOf(0) }
    selectedState = remember(backStackEntry) {
        bottomNavigationItems.indexOfFirst { backStackEntry?.destination?.route == it.destination.route }
    }

    val areScaffoldBarsVisible = remember(backStackEntry) {
        backStackEntry?.destination?.route == Route.HomeScreen.route || backStackEntry?.destination?.route == Route.BookmarkScreen.route
    }

    val viewModel = viewModel<JobNavigatorViewModel>()
    val snackbarHostState = remember { GenericSnackbarHostState<SnackbarMessage>() }
    val lazyPagingItems = viewModel.jobsPagingDataFlow.collectAsLazyPagingItems()

    Scaffold(
        snackbarHost = {
            GenericSnackBarHost(snackbarHostState) { snackbarData ->
                SmartSnackbar(
                    snackbarData = snackbarData,
                    actionLabel = snackbarData.actionLabel,
                )
            }
        },
        modifier = modifier,
        topBar = {
            if (areScaffoldBarsVisible) {
                NavigatorTopBar(route = bottomNavigationItems[selectedState].destination)
            }
        },
        bottomBar = {
            if (areScaffoldBarsVisible) {
                BottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedState,
                    onClick = {
                        val route = bottomNavigationItems[it].destination
                        navigateToBottom(navController, route.route)
                    }
                )
            }
        },
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Route.HomeScreen.route,
            route = Route.JobNavigator.route
        ) {

            composable(route = Route.HomeScreen.route) {
                val bookmarkedJobs = viewModel.bookmarkedJobs.collectAsState()
                HandlePagingDataLoadState(
                    snackbarHostState = snackbarHostState,
                    lazyPagingItems = lazyPagingItems,
                )

                HomeScreen(
                    jobItems = lazyPagingItems,
                    bookmarkedJobs = bookmarkedJobs.value,
                    onNavigateToDetails = { job ->
                        navigateToDetails(navController, job)
                    },
                    onBookmarkClick = viewModel::toggleBookmark
                )
            }

            composable(route = Route.BookmarkScreen.route) {
                val bookmarkedJobs = viewModel.bookmarkedJobs.collectAsState()
                BookmarkScreen(
                    jobList = bookmarkedJobs.value, onNavigateToDetails = { job ->
                        navigateToDetails(navController, job)
                    },
                    onBookmarkClick = viewModel::toggleBookmark
                )
            }

            composable(route = Route.DetailScreen.route) {
                val job =
                    navController.previousBackStackEntry?.savedStateHandle?.get<JobEntity?>("job")
                job?.let {
                    DetailScreen(job = job)
                }
            }
        }
    }
}

@Composable
fun HandlePagingDataLoadState(
    snackbarHostState: GenericSnackbarHostState<SnackbarMessage>,
    lazyPagingItems: LazyPagingItems<JobEntity>,
) {
    val loadState = lazyPagingItems.loadState
    var retryCount by remember { mutableIntStateOf(0) }
    LaunchedEffect(loadState) {
        when {
            loadState.append is LoadState.Error || loadState.refresh is LoadState.Error -> {
                val message = SnackbarMessage.Filling(
                    message = "Couldn't fetch jobs",
                    durationInMs = ((retryCount + 3) * 1000).coerceAtMost(8000),
                    onFillAction = {
                        retryCount++
                        lazyPagingItems.retry()
                    }, fillColor = Gray
                )
                snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = "Retry",
                    duration = GenericSnackbarDuration.Indefinite
                ).also {
                    when (it) {
                        SnackbarResult.Dismissed -> {}
                        SnackbarResult.ActionPerformed -> {
                            Log.d("TAG", "HandlePagingDataLoadState: Retrying")
                            retryCount++
                            lazyPagingItems.retry()
                        }
                    }
                }

            }

            loadState.append is LoadState.NotLoading && loadState.refresh is LoadState.NotLoading -> {
                retryCount = 0
            }
        }
    }

}

private fun navigateToBottom(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) { saveState = true }
        }
        restoreState = true
        launchSingleTop = true
    }

}

private fun navigateToDetails(navController: NavController, job: JobEntity) {
    navController.currentBackStackEntry?.savedStateHandle?.set("job", job)
    navController.navigate(Route.DetailScreen.route)
}
