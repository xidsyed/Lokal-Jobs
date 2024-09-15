package com.app.lokaljobs.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.lokaljobs.data.local.Job
import com.app.lokaljobs.presentation.screens.BookmarkScreen
import com.app.lokaljobs.presentation.screens.DetailScreen
import com.app.lokaljobs.presentation.screens.HomeScreen
import com.app.lokaljobs.presentation.screens.JobNavigatorViewModel
import com.app.lokaljobs.presentation.screens.components.BottomNavigation
import com.app.lokaljobs.presentation.screens.components.BottomNavigationItem
import com.app.lokaljobs.presentation.screens.components.NavigatorTopBar
import com.cinderella.lokaljobs.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobNavigator() {

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
        bottomNavigationItems.indexOfFirst {
            backStackEntry?.destination?.route == it.destination.route
        }
    }

    val areScaffoldBarsVisible = remember(backStackEntry) {
        backStackEntry?.destination?.route == Route.HomeScreen.route || backStackEntry?.destination?.route == Route.BookmarkScreen.route
    }

    val viewModel = viewModel<JobNavigatorViewModel>()


    Scaffold(
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
                    })
            }
        },
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Route.BookmarkScreen.route,
            route = Route.JobNavigator.route
        ) {

            composable(route = Route.HomeScreen.route) {
                val jobItems = viewModel.jobs.collectAsLazyPagingItems()
                val bookmarkedJobs = viewModel.bookmarkedJobs.collectAsState()
                HomeScreen(
                    jobItems = jobItems,
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
                val job = navController.previousBackStackEntry?.savedStateHandle?.get<Job?>("job")
                job?.let {
                    DetailScreen(job)
                }
            }
        }
    }
}

@Preview
@Composable
fun JobNavigatorPreview() {
    JobNavigator()
}


fun navigateToBottom(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) { saveState = true }
        }
        restoreState = true
        launchSingleTop = true
    }

}

private fun navigateToDetails(navController: NavController, job: Job) {
    navController.currentBackStackEntry?.savedStateHandle?.set("job", job)
    navController.navigate(Route.DetailScreen.route)
}
