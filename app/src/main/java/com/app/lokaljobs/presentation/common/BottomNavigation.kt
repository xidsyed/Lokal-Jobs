package com.app.lokaljobs.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.app.lokaljobs.Dimens
import com.app.lokaljobs.presentation.navigation.Route
import com.app.lokaljobs.ui.theme.Background
import com.app.lokaljobs.ui.theme.DarkGray
import com.app.lokaljobs.ui.theme.Gray
import com.app.lokaljobs.ui.theme.LokalJobsTheme
import com.app.lokaljobs.ui.theme.Surface
import com.cinderella.lokaljobs.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItem>,
    selected: Int,
    onClick: (Int) -> Unit,
    scrollBehavior: BottomAppBarScrollBehavior? = null
) {
    BottomAppBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = Background,
        scrollBehavior = scrollBehavior
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = index == selected
            NavigationBarItem(
                selected = isSelected,
                onClick = { onClick(index) },
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        painterResource(id = if (isSelected) item.iconSelected else item.icon),
                        contentDescription = null,
                        modifier = Modifier.size(Dimens.NavIconSize)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = TextStyle(color = DarkGray, fontWeight = FontWeight.Medium)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DarkGray,
                    unselectedIconColor = Gray,
                    selectedTextColor = DarkGray,
                    unselectedTextColor = Gray,
                    indicatorColor = Surface
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomNavigationPreview() {
    LokalJobsTheme {
        BottomNavigation(items = listOf(
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
                destination = Route.BookmarkScreen,
            ),
        ), selected = 0, onClick = {})
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int,
    val label: String,
    val destination: Route
)

