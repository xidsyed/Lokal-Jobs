package com.app.lokaljobs.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.lokaljobs.ui.theme.Highlight
import com.app.lokaljobs.ui.theme.OnHighlightDark
import com.cinderella.lokaljobs.R

@Composable
fun ScrollToTopButton(
    isVisible: Boolean,
    isDark: Boolean = false,
    size: Dp = 44.dp,
    backgroundColor: Color = if (isDark) OnHighlightDark else Highlight,
    foregroundColor: Color = if (isDark) Highlight else OnHighlightDark,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = scaleIn(
            initialScale = 0.8f,
            animationSpec = tween(250, easing = EaseOutExpo)
        ),
        exit = scaleOut(
            targetScale = 0.8f,
            animationSpec = tween(250, easing = EaseInExpo)
        )
    ) {
        Button(
            isDark = isDark,
            size = size,
            backgroundColor = backgroundColor,
            foregroundColor = foregroundColor,
            onClick = onClick
        )
    }
}

@Composable
private fun Button(
    modifier: Modifier = Modifier,
    isDark: Boolean = false,
    size: Dp = 44.dp,
    backgroundColor: Color = if (isDark) OnHighlightDark else Highlight,
    foregroundColor: Color = if (isDark) Highlight else OnHighlightDark,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .dropShadow(
                offsetX = 0.dp,
                offsetY = 3.dp,
                blur = 1.dp,
                shape = CircleShape,
                color = Color(0x1A000000)
            )
            .size(size)
            .clip(CircleShape)
            .clipToBounds()
            .background(backgroundColor)
            .clickable(enabled = true, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(0.3f),
            painter = painterResource(R.drawable.arrow),
            tint = foregroundColor,
            contentDescription = "Scroll To Top"
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 50, heightDp = 50)
@Composable
private fun ScrollToTopButtonPreview() {
    Button(
        isDark = false,
        onClick = {},
        foregroundColor = OnHighlightDark,
        backgroundColor = Highlight
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 50, heightDp = 50)
@Composable
private fun ScrollToTopButtonDarkPreview() {
    Button(isDark = true, onClick = {})
}