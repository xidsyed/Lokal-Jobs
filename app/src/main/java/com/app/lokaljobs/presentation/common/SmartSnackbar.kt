package com.app.lokaljobs.presentation.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.ui.theme.DarkGray
import com.app.lokaljobs.ui.theme.Highlight
import com.app.lokaljobs.ui.theme.Surface
import kotlinx.coroutines.delay


@Composable
fun SmartSnackbar(
    modifier: Modifier = Modifier,
    snackbarData: GenericSnackbarData<SnackbarMessage>,
    actionLabel: String?,
) {
    val snackbarColors = snackbarData.message.snackbarColors
    when (snackbarData.message) {
        is SnackbarMessage.Default -> {
            DefaultSnackbar(
                modifier = modifier,
                message = snackbarData.message.message,
                actionLabel = actionLabel,
                performAction = snackbarData::performAction,
                dismissAction = snackbarData::dismiss,
                snackbarColors = snackbarColors
            )
        }

        is SnackbarMessage.Filling -> {
            val fillingData = snackbarData.message as SnackbarMessage.Filling
            RetrySnackbar(
                modifier = modifier,
                message = fillingData.message,
                actionLabel = actionLabel,
                durationInMs = fillingData.durationInMs,
                performAction = snackbarData::performAction,
                dismissAction = snackbarData::dismiss,
                onFillAction = fillingData.onFillAction,
                fillColor = fillingData.fillColor,
                snackbarColors = snackbarColors
            )
        }

        is SnackbarMessage.Disabled -> {
            DisabledSnackbar(
                modifier = modifier,
                message = snackbarData.message.message,
                actionLabel = actionLabel,
                performAction = snackbarData::performAction,
                dismissAction = snackbarData::dismiss,
                snackbarColors = snackbarColors
            )
        }
    }
}

@Composable
private fun Modifier.snackbarShape(background: Color): Modifier {
    return this
        .fillMaxWidth()
        .requiredHeight(64.dp)
        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        .clip(RoundedCornerShape(4.dp))
        .background(color = background)

}


@Composable
private fun DefaultSnackbar(
    modifier: Modifier = Modifier,
    message: String,
    actionLabel: String?,
    performAction: () -> Unit,
    dismissAction: () -> Unit,
    snackbarColors: SnackbarColors
) {
    Box(
        modifier = modifier
            .snackbarShape(snackbarColors.background)
    ) {
        SnackbarContent(
            message = message,
            actionLabel = actionLabel,
            performAction = performAction,
            dismissAction = dismissAction,
            messageColor = snackbarColors.messageColor,
            actionColor = snackbarColors.actionColor,
        )
    }
}


class SnackbarColors(
    val messageColor: Color,
    val actionColor: Color,
    val background: Color
) {
    companion object {
        val Default = SnackbarColors(
            messageColor = Surface,
            actionColor = Highlight,
            background = DarkGray
        )
        val Disabled = SnackbarColors(
            messageColor = LightGray,
            actionColor = LightGray,
            background = Gray
        )
    }
}

@Composable
private fun RetrySnackbar(
    modifier: Modifier = Modifier,
    durationInMs: Int,
    message: String,
    actionLabel: String?,
    fillColor: Color,
    snackbarColors: SnackbarColors,
    performAction: () -> Unit,
    dismissAction: () -> Unit,
    onFillAction: () -> Unit
) {
    var animationComplete by remember { mutableStateOf(false) }

    if (!animationComplete) {
        FillingSnackbar(
            modifier = modifier,
            durationInMs = durationInMs,
            message = message,
            actionLabel = actionLabel,
            fillColor = fillColor,
            snackbarColors = snackbarColors,
            performAction = performAction,
            dismissAction = dismissAction,
            onFillAction = { animationComplete = true }
        )
    } else {
        DisabledSnackbar(
            message = message,
            actionLabel = "Retrying...",
            performAction = {  },
            dismissAction = {  },
            snackbarColors = SnackbarColors.Disabled
        )
        LaunchedEffect(Unit) {
            delay(1000)
            onFillAction()
        }
    }

}

@Composable
private fun FillingSnackbar(
    modifier: Modifier = Modifier,
    durationInMs: Int,
    message: String,
    actionLabel: String?,
    fillColor: Color,
    snackbarColors: SnackbarColors,
    performAction: () -> Unit,
    dismissAction: () -> Unit,
    onFillAction: () -> Unit

) {
    var startAnimation by remember { mutableStateOf(false) }
    val animationProgress by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = durationInMs, easing = LinearEasing), label = "",
        finishedListener = {
            onFillAction()
        }
    )
    LaunchedEffect(Unit) { startAnimation = true }

    Box(
        modifier = modifier
            .snackbarShape(snackbarColors.background)
            .fillingBackground(
                fillFraction = animationProgress,
                fillColor = fillColor,
            )
    ) {
        SnackbarContent(
            message = message,
            actionLabel = actionLabel,
            performAction = performAction,
            dismissAction = dismissAction,
            messageColor = Surface,
            actionColor = Highlight,
        )


    }
}


@Composable
private fun SnackbarContent(
    message: String,
    messageColor: Color,
    actionColor: Color,
    actionLabel: String?,
    performAction: () -> Unit,
    actionClickable: Boolean = true,
    dismissAction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = messageColor,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f, fill = true),
            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
            maxLines = 2
        )

        actionLabel?.let { actionLabel ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 8.dp, horizontal = 12.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .clickable(actionClickable) { performAction() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = actionLabel,
                    color = actionColor,
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun DisabledSnackbar(
    modifier: Modifier = Modifier,
    message: String,
    actionLabel: String?,
    snackbarColors: SnackbarColors,
    performAction: () -> Unit,
    dismissAction: () -> Unit
) {
    Box(modifier = modifier.snackbarShape(snackbarColors.background)) {
        SnackbarContent(
            message = message,
            actionLabel = actionLabel,
            performAction = performAction,
            dismissAction = dismissAction,
            messageColor = snackbarColors.messageColor,
            actionColor = snackbarColors.actionColor,
            actionClickable = false
        )
    }
}


@Preview(showBackground = false)
@Composable
fun PreviewDisabledSnackbar() {
    DisabledSnackbar(
        message = "This is a disabled snackbar",
        actionLabel = "Disabled",
        performAction = {},
        dismissAction = {},
        snackbarColors = SnackbarColors.Disabled
    )
}

@Preview(showBackground = false)
@Composable
fun PreviewRetrySnackbar() {
    RetrySnackbar(
        message = "This is a filling snackbar",
        actionLabel = "Retry",
        performAction = {},
        dismissAction = {},
        fillColor = Gray,
        onFillAction = {},
        durationInMs = 5000,
        snackbarColors = SnackbarColors.Default
    )
}

@Preview(showBackground = false)
@Composable
fun PreviewFillingSnackbar() {
    FillingSnackbar (
        message = "This is a filling snackbar",
        actionLabel = "Retry",
        performAction = {},
        dismissAction = {},
        fillColor = Gray,
        onFillAction = {},
        durationInMs = 5000,
        snackbarColors = SnackbarColors.Default
    )
}

@Preview(showBackground = false)
@Composable
fun PreviewDefaultSnackbar() {
    DefaultSnackbar(
        message = "This is a default snackbar",
        actionLabel = "Action",
        performAction = {},
        dismissAction = {},
        snackbarColors = SnackbarColors.Default
    )
}