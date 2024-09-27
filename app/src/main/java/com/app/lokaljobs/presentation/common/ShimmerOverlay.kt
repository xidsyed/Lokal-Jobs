package com.app.lokaljobs.presentation.common

import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.EaseInOutExpo
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShimmerOverlay(
    isShimmering: Boolean, // Toggle shimmer on or off
    shimmerColor: Color = Color.White,
    modifier: Modifier = Modifier,
    durationMillis: Int = 2000,
    delayMillis: Int = 100,
    animationSpec: DurationBasedAnimationSpec<Float> = tween(durationMillis, delayMillis, EaseInOutExpo),
    content: @Composable () -> Unit
) {
    // Shimmer animation setup
    val infiniteTransition = rememberInfiniteTransition(label = "ShimmerTransition")
    val shimmerProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animationSpec),
        label = "ShimmerProgress"
    )

    // Shimmer brush
    val shimmerBrush = remember(shimmerProgress) {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val initialXOffset = -size.width
                val totalSweepDistance = size.width * 2
                val currentPosition = initialXOffset + totalSweepDistance * shimmerProgress

                return LinearGradientShader(
                    colors = listOf(Color.Transparent, shimmerColor, Color.Transparent),
                    from = Offset(currentPosition, 0f),
                    to = Offset(currentPosition + size.width, 0f)
                )
            }
        }
    }

    Box(
        modifier = modifier
    ) {
        // Content inside the Box
        content()

        // Overlay shimmer if isShimmering is true
        if (isShimmering) {
            /*Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(brush = shimmerBrush)
            )*/
            Text("Something", style = LocalTextStyle.current.copy(brush = shimmerBrush))
        }
    }
}


@Preview()
@Composable
fun ShimmeringBoxExample() {
    // Toggle shimmer on/off
    var isShimmering by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Column {

            ShimmerOverlay(
                isShimmering = isShimmering, // You can toggle this on or off
                shimmerColor = Color.White
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "LOADING",
                        style = LocalTextStyle.current.copy(
                            fontSize = 20.sp,
                            letterSpacing = 5.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
            // Toggle Button for shimmer animation
            Button(onClick = { isShimmering = !isShimmering }) {
                Text(text = if (isShimmering) "Stop Shimmer" else "Start Shimmer")
            }
        }

    }
}