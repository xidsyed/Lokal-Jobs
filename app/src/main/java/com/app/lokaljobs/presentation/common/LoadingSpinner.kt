package com.app.lokaljobs.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.lokaljobs.ui.theme.LightGray

@Composable
fun LoadingSpinner(
    padding: PaddingValues = PaddingValues(vertical = 16.dp),
    size: Dp = 64.dp,
    strokeWidth: Dp = 3.dp,
    color: Color = LightGray
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(size),
            color = color,
            strokeWidth = 2.dp
        )
    }
}