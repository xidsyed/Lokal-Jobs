package com.app.lokaljobs.presentation.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray

fun Modifier.fillingBackground(
    fillFraction: Float, fillColor: Color = Gray
): Modifier {
    return this
        .drawBehind {
            val width = size.width * fillFraction
            drawRect(
                color = fillColor,
                topLeft = Offset.Zero,
                size = Size(width, size.height)
            )
        }
}