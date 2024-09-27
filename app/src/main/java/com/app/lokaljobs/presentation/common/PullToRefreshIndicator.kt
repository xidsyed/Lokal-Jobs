package com.app.lokaljobs.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.presentation.DummyBookmarkJobCardList
import com.app.lokaljobs.presentation.formatElapsedTime
import com.app.lokaljobs.ui.theme.Gray
import com.cinderella.lokaljobs.R
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun PullToRefreshIndicator(
    modifier: Modifier = Modifier,
    lastRefreshedAt: Long,
    refreshIndicatorState: RefreshIndicatorState,
    pullToRefreshProgress: Float,
    maxHeight: Int = 140,
    animationDuration: Int = 250,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(
                (pullToRefreshProgress * 60)
                    .roundToInt()
                    .coerceIn(0, maxHeight).dp
            )
            .clipToBounds(),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {

            val easing = EaseOutExpo
            val fadeEasing = EaseInOut
            val slowFloatAnimSpec = tween<Float>(animationDuration / 2, easing = easing)
            val fadeAnimSpec = tween<Float>(animationDuration, easing = fadeEasing)
            val slowIntSizeAnimSpec =
                tween<IntSize>(durationMillis = animationDuration, easing = easing)

            val showIcon by remember(refreshIndicatorState) {
                derivedStateOf {
                    refreshIndicatorState == RefreshIndicatorState.PullingDown ||
                            refreshIndicatorState == RefreshIndicatorState.ReachedThreshold
                }
            }
            AnimatedVisibility(
                visible = showIcon,
                enter = fadeIn(fadeAnimSpec),
                exit = shrinkHorizontally(slowIntSizeAnimSpec) + fadeOut(fadeAnimSpec)
            ) {
                val rotationTarget by remember(refreshIndicatorState) {
                    derivedStateOf {
                        mutableFloatStateOf(
                            if (refreshIndicatorState == RefreshIndicatorState.PullingDown) 180f
                            else 0f
                        )
                    }.value
                }
                val rotationProgress by animateFloatAsState(
                    targetValue = rotationTarget,
                    animationSpec = slowFloatAnimSpec,
                    label = "rotationProgress"
                )
                Icon(
                    painter = painterResource(id = R.drawable.double_down_arrow),
                    contentDescription = "Keyboard double arrow down",
                    modifier = Modifier
                        .requiredSize(size = 16.dp)
                        .rotate(rotationProgress),
                    tint = Color.Unspecified
                )
            }


            val isShimmering by remember(refreshIndicatorState) {
                derivedStateOf { refreshIndicatorState == RefreshIndicatorState.Refreshing }
            }

            val isTextVisible by remember(refreshIndicatorState) {
                derivedStateOf { refreshIndicatorState != RefreshIndicatorState.Default }
            }

            var text by remember { mutableStateOf("Last updated 0 seconds ago") }
            LaunchedEffect(refreshIndicatorState) {
                val update = when (refreshIndicatorState) {
                    RefreshIndicatorState.PullingDown -> {
                        if (lastRefreshedAt != -1L) {
                            val elapsedTime =
                                formatElapsedTime(lastRefreshedAt, System.nanoTime())
                            "Last updated $elapsedTime ago"
                        } else "Pull To Refresh"
                    }

                    RefreshIndicatorState.ReachedThreshold -> "Release to refresh"
                    RefreshIndicatorState.Refreshing -> "Refreshing"
                    else -> null
                }
                update?.let { text = update }
            }

            AnimatedVisibility(
                visible = isTextVisible,
                modifier = Modifier.animateContentSize(slowIntSizeAnimSpec),
                enter = fadeIn(fadeAnimSpec),
                exit = fadeOut(fadeAnimSpec)
            )
            {
                val textStyle =
                    if (refreshIndicatorState == RefreshIndicatorState.Refreshing) TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Gray
                    )
                    else TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Normal, color = Gray)
                ShimmeringText(
                    text = text,
                    isShimmering = isShimmering,
                    durationMillis = 600,
                    delayMillis = 200,
                    color = Gray,
                    shimmerColor = Color.White,
                    style = textStyle,
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
            }
        }
    }
}

enum class RefreshIndicatorState { Default, PullingDown, ReachedThreshold, Refreshing }

@Preview(showBackground = true)
@Composable
private fun RefreshPullDownPreview() {
    val lastRefreshedAt = -1L
    var refreshIndicatorState by remember { mutableStateOf(RefreshIndicatorState.Default) }
    var targetValue by remember { mutableFloatStateOf(0f) }
    val timeInterval = 2000
    val pullToRefreshProgress by animateFloatAsState(
        targetValue = targetValue,
        label = "pullToRefresh",
        animationSpec = TweenSpec(durationMillis = timeInterval, easing = FastOutSlowInEasing),
    )
    LaunchedEffect(Unit) {
        while (true) {
            when (refreshIndicatorState) {
                RefreshIndicatorState.Default -> {
                    targetValue = 0.8f
                    refreshIndicatorState = RefreshIndicatorState.PullingDown
                }

                RefreshIndicatorState.PullingDown -> {
                    targetValue = 1f
                    refreshIndicatorState = RefreshIndicatorState.ReachedThreshold
                }

                RefreshIndicatorState.ReachedThreshold -> {
                    targetValue = 0.5f
                    refreshIndicatorState = RefreshIndicatorState.Refreshing
                }

                RefreshIndicatorState.Refreshing -> {
                    targetValue = 0f
                    refreshIndicatorState = RefreshIndicatorState.Default
                }
            }
            delay(timeInterval.toLong())
        }
    }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        PullToRefreshIndicator(
            lastRefreshedAt = lastRefreshedAt,
            refreshIndicatorState = refreshIndicatorState,
            pullToRefreshProgress = pullToRefreshProgress
        )
        DummyBookmarkJobCardList()
    }
}
