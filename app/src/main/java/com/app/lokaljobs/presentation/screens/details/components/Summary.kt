package com.app.lokaljobs.presentation.screens.details.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.data.local.JobEntity
import com.app.lokaljobs.ui.theme.DarkGray
import com.app.lokaljobs.ui.theme.Gray
import com.app.lokaljobs.ui.theme.Surface
import com.cinderella.lokaljobs.R


@Composable
fun Summary(job: JobEntity, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Surface)
                .padding(
                    horizontal = 24.dp,
                    vertical = 18.dp
                )
        ) {
            val list = listOfNotNull(
                if (job.minSalary != null && job.maxSalary != null) {
                    SummaryItemHolder(R.drawable.icon_rupee, "${job.minSalary} - ${job.maxSalary}")
                } else null,
                SummaryItemHolder(R.drawable.icon_place, job.location),
                SummaryItemHolder(R.drawable.icon_vacancy, "${job.openingCount} slots"),
                SummaryItemHolder(R.drawable.icon_fees, "No Fees")
            )
            repeat(list.size) {
                SummaryItem(list[it])
            }
        }
    }
}

@Composable
private fun SummaryItem(item: SummaryItemHolder, modifier: Modifier = Modifier) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .requiredSize(size = 24.dp)
        ) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = "Detail Icon",
                tint = Gray,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Text(
            text = item.description,
            color = DarkGray,
            lineHeight = 1.07.em,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }

}


private data class SummaryItemHolder(
    @DrawableRes val icon: Int,
    val description: String
)
