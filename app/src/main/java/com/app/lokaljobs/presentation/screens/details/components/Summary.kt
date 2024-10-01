package com.app.lokaljobs.presentation.screens.details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.presentation.getDummyJob
import com.app.lokaljobs.ui.theme.Gray
import com.app.lokaljobs.ui.theme.LokalTypography.subtitle2Lokal
import com.cinderella.lokaljobs.R


@Composable
fun Summary(
    modifier: Modifier = Modifier,
    list: List<SummaryItemHolder>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 24.dp
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SummaryItemColumn(list.subList(0, list.size / 2))
        SummaryItemColumn(list.subList(list.size / 2, list.size))
    }
}

@Composable
private fun SummaryItem(item: SummaryItemHolder, modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = "Detail Icon",
                tint = Gray,
                modifier = Modifier.requiredSize(12.dp)
            )
            Text(
                text = item.title, color = Gray, style = subtitle2Lokal
            )
        }
        Text(
            text = item.description, color = Gray, lineHeight = 1.em, style = TextStyle(
                fontSize = 16.sp, fontWeight = FontWeight.SemiBold
            )
        )
    }

}

@Composable
private fun RowScope.SummaryItemColumn(itemList: List<SummaryItemHolder>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        modifier = Modifier
            .weight(1f)
    ) {
        itemList.forEachIndexed { index, item ->
            SummaryItem(item)
            if (index != itemList.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                    color = Color.Black.copy(0.1f)
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewSummary() {
    val job = getDummyJob()
    val list = listOfNotNull(
        SummaryItemHolder(
            R.drawable.rupee, "Salary", "${job.minSalary ?: ""} - ${job.maxSalary ?: ""}"
        ),
        SummaryItemHolder(R.drawable.place, "Location", job.location),
        SummaryItemHolder(R.drawable.person, "Vacancy", "${job.openingCount} slots"),
        SummaryItemHolder(R.drawable.fees, "Fees", "None"),
        SummaryItemHolder(R.drawable.work_history, "Experience", job.experience),
        SummaryItemHolder(R.drawable.call, "Contact", "HR")
    )

    Summary(list = list)
}

class SummaryItemHolder(
    @DrawableRes val icon: Int, val title: String, val description: String
)
