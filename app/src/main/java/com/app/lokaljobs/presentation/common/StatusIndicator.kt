package com.app.lokaljobs.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.ui.theme.ErrorRed
import com.app.lokaljobs.ui.theme.Surface
import com.cinderella.lokaljobs.R
import kotlinx.coroutines.delay

@Composable
fun StatusIndicator(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    message: String,
    backgroundColor: Color,
    foregroundColor: Color
) {
    val sizeDuration = 500
    val fadeDuration = 500
    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(
            animationSpec = tween(sizeDuration, easing = EaseOutExpo),
            expandFrom = Alignment.Top
        ) + fadeIn(tween(fadeDuration, easing = EaseOutExpo)),
        exit = shrinkVertically(
            animationSpec = tween(
                fadeDuration,
                easing = EaseOutExpo
            )
        ) + fadeOut(
            tween(
                sizeDuration,
                easing = EaseOutCubic
            )
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(32.dp)
                .background(color = backgroundColor)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cloud_off),
                contentDescription = "Cloud off",
                tint = foregroundColor,
                modifier = Modifier
                    .requiredSize(size = 18.dp)
            )
            Text(
                text = message,
                color = foregroundColor,
                style = TextStyle(
                    fontSize = 12.sp, fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StatusIndicatorPreview() {
    var isVisible by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            isVisible = !isVisible
        }
    }
    StatusIndicator(Modifier, isVisible, "Connection Lost", ErrorRed, Surface)
}