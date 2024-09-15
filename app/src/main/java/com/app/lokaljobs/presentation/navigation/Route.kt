package com.app.lokaljobs.presentation.navigation

sealed class Route(val route: String) {
    object JobNavigator : Route(route = "jobNavigator")
    object BookmarkScreen : Route(route = "bookmarkScreen")
    object DetailScreen : Route(route = "detailScreen")
    object HomeScreen : Route(route = "homeScreen")
}
