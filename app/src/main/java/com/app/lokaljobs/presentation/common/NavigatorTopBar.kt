package com.app.lokaljobs.presentation.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.presentation.navigation.Route
import com.app.lokaljobs.ui.theme.DarkGray
import com.app.lokaljobs.ui.theme.Highlight
import com.app.lokaljobs.ui.theme.OnHighlightDark
import com.cinderella.lokaljobs.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigatorTopBar(
    modifier: Modifier = Modifier,
    route: Route,
    scrollBehaviour: TopAppBarScrollBehavior? = null
) {
    val topAppBarColor = if (route is Route.HomeScreen) Highlight else Color.White
    TopAppBar(
        modifier = modifier
            .padding(vertical = 0.dp)
            .fillMaxWidth(),
        scrollBehavior = scrollBehaviour,
        expandedHeight = 80.dp,
        title = {
            val slideDuration = 500
            val fadeInDuration = 300
            val fadeOutDuration = 50
            AnimatedContent(
                targetState = route,
                transitionSpec = {
                    if (targetState is Route.HomeScreen) {
                        (slideInHorizontally(
                            initialOffsetX = { (-it * 0.8).toInt() },
                            animationSpec = tween(slideDuration, easing = EaseOutExpo)
                        ) + fadeIn(animationSpec = tween(fadeInDuration))).togetherWith(
                            slideOutHorizontally(
                                targetOffsetX = { (it * 0.8).toInt() },
                                animationSpec = tween(slideDuration, easing = EaseOutExpo)
                            ) + fadeOut(animationSpec = tween(fadeOutDuration))
                        )
                    } else {
                        (slideInHorizontally(
                            initialOffsetX = { (it * 0.8).toInt() },
                            animationSpec = tween(slideDuration, easing = EaseOutExpo)
                        ) + fadeIn(animationSpec = tween(fadeInDuration))).togetherWith(
                            slideOutHorizontally(
                                targetOffsetX = { (-it * 0.8).toInt() },
                                animationSpec = tween(slideDuration, easing = EaseOutExpo)
                            ) + fadeOut(animationSpec = tween(fadeOutDuration))
                        )
                    }
                }, label = ""
            ) { targetState ->
                when (targetState) {
                    is Route.HomeScreen -> {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                12.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.lokal),
                                tint = OnHighlightDark,
                                contentDescription = "Lokal Icon",
                                modifier = Modifier.requiredSize(size = 30.dp)
                            )
                            Text(lineHeight = 1.em, text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = DarkGray,
                                        fontSize = 36.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                ) { append("Lokal ") }
                                withStyle(
                                    style = SpanStyle(
                                        color = OnHighlightDark,
                                        fontSize = 36.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                ) { append("Jobs") }
                            })
                        }
                    }

                    else -> {
                        Text(
                            text = "Bookmarks",
                            modifier = Modifier.fillMaxWidth(),
                            color = DarkGray,
                            lineHeight = 1.sp,
                            style = TextStyle(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 36.sp,
                                color = DarkGray
                            )
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = topAppBarColor,
            scrolledContainerColor = topAppBarColor,
            titleContentColor = topAppBarColor
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TopAppBarPreview() {
    NavigatorTopBar(route = Route.BookmarkScreen, scrollBehaviour = null)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TopAppBarHomeScreenPreview() {
    NavigatorTopBar(route = Route.HomeScreen, scrollBehaviour = null)
}