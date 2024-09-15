package com.app.lokaljobs.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.app.lokaljobs.ui.theme.DarkGray
import com.app.lokaljobs.ui.theme.Highlight
import com.app.lokaljobs.ui.theme.OnHighlightDark


@Composable
fun FilterRow(filterList: List<String>, selected: Int, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(width = 412.dp)
            .background(color = Color.White)
            .padding(horizontal = 16.dp)
    ) {
        repeat(filterList.size) { index ->
            val isSelected = selected == index
            InputChip(
                label = {
                    Text(
                        text = filterList[index],
                        textAlign = TextAlign.Center,
                        lineHeight = 1.43.em,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .wrapContentHeight(align = Alignment.CenterVertically)
                    )
                },
                leadingIcon = {
                    if (isSelected) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Selected Icon",
                            tint = OnHighlightDark,
                            modifier = Modifier
                                .requiredSize(size = 18.dp)
                        )
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.Transparent,
                    iconColor = DarkGray,
                    labelColor = DarkGray,
                    selectedContainerColor = Highlight,
                    selectedLabelColor = OnHighlightDark,
                    selectedLeadingIconColor = OnHighlightDark
                ),
                border = InputChipDefaults.inputChipBorder(
                    borderColor = DarkGray,
                    enabled = true,
                    selected = isSelected
                ),
                selected = isSelected,
                onClick = { }
            )

        }
    }
}


@Preview()
@Composable
private fun FilterRowPreview() {
    FilterRow(listOf("Created", "Bookmarked"), 0)
}