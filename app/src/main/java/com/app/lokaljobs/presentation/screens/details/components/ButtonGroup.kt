package com.app.lokaljobs.presentation.screens.details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.lokaljobs.ui.theme.Highlight
import com.app.lokaljobs.ui.theme.LokalTypography.button1Lokal
import com.app.lokaljobs.ui.theme.OnHighlight
import com.app.lokaljobs.ui.theme.OnHighlightDark
import com.cinderella.lokaljobs.R

@Composable
fun ButtonGroup(
    modifier: Modifier = Modifier,
    list: List<ButtonData>,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        list.forEach { buttonData ->
            buttonData.apply {
                Box(
                    Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(backgroundColor)
                        .clickable { }
                        .padding(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp,
                            Alignment.CenterHorizontally
                        )
                    ) {
                        if (showText) {
                            Text(text = text, style = button1Lokal, color = foregroundColor)
                        }
                        if (showIcon) {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = text,
                                tint = foregroundColor,
                                modifier = Modifier
                                    .requiredSize(size = 16.dp)
                            )
                        }
                    }

                }
            }

        }
    }
}

class ButtonData(
    @DrawableRes val icon: Int,
    val text: String,
    val backgroundColor: Color,
    val foregroundColor: Color,
    val showIcon: Boolean = true,
    val showText: Boolean = false
)

@Preview(showBackground = true)
@Composable
fun CallButtonGroupPreview() {
    Box(Modifier.padding(vertical = 16.dp)) {
        ButtonGroup(
            modifier = Modifier
                .fillMaxWidth()
                .background(Highlight)
                .padding(horizontal = 16.dp)
                .height(80.dp),
            list = listOf(
                ButtonData(R.drawable.call, "Call", OnHighlight, OnHighlightDark),
                ButtonData(R.drawable.whatsapp, "Whatsapp", OnHighlight, OnHighlightDark),
                ButtonData(R.drawable.mail, "Mail", OnHighlight, OnHighlightDark),
            ),
        )
    }
}
