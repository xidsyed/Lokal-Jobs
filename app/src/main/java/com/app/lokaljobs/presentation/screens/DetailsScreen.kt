package com.app.lokaljobs.presentation.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.data.local.Job
import com.app.lokaljobs.presentation.getDummyJob
import com.app.lokaljobs.presentation.getTime
import com.app.lokaljobs.ui.theme.DarkGray
import com.app.lokaljobs.ui.theme.Gray
import com.app.lokaljobs.ui.theme.Green
import com.app.lokaljobs.ui.theme.LokalJobsTheme
import com.app.lokaljobs.ui.theme.OnHighlight
import com.app.lokaljobs.ui.theme.Surface
import com.cinderella.lokaljobs.R

@Composable
fun DetailScreen(job: Job) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
    ) {
        // Header
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 32.dp,
                    bottom = 8.dp
                )
        ) {
            Text(
                text = job.companyName,
                color = DarkGray,
                lineHeight = 1.2.em,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = job.category,
                color = Gray,
                lineHeight = 1.41.em,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        // Summary Block
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
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
                        SummaryItem(R.drawable.icon_rupee, "${job.minSalary} - ${job.maxSalary}")
                    } else null,
                    SummaryItem(R.drawable.icon_place, job.location),
                    SummaryItem(R.drawable.icon_vacancy, "${job.openingCount} slots"),
                    SummaryItem(R.drawable.icon_fees, "No Fees")
                )
                repeat(list.size) {
                    Summary(list[it])
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            JobDescription(job.title, job.otherDetails ?: "", job.getTime() ?: "")
            ButtonGroup()
        }
    }

}

@Composable
private fun ButtonGroup() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .requiredWidth(width = 180.dp)
            .padding(vertical = 16.dp)
    ) {

        Button(
            onClick = { },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = DarkGray),
            contentPadding = PaddingValues(all = 12.dp),
            modifier = Modifier.weight(weight = 1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .requiredWidth(width = 114.dp)
            ) {
                Text(
                    text = "Call",
                    color = OnHighlight,
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_call),
                    contentDescription = "Phone",
                    colorFilter = ColorFilter.tint(OnHighlight),
                    modifier = Modifier
                        .requiredSize(size = 16.dp)
                )
            }
        }
        Button(
            onClick = { },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffffe399)),
            contentPadding = PaddingValues(all = 12.dp),
            modifier = Modifier
                .requiredHeight(height = 40.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .requiredHeight(height = 40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_whatsapp_logo),
                    contentDescription = "Whatsapp",
                    colorFilter = ColorFilter.tint(Green),
                    modifier = Modifier
                        .requiredSize(size = 18.dp)
                )
            }
        }
    }
}

data class SummaryItem(
    @DrawableRes val icon: Int,
    val description: String
)

@Composable
fun Summary(item: SummaryItem, modifier: Modifier = Modifier) {
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

@Composable
private fun ColumnScope.JobDescription(title: String, body: String, postedDate: String) {
    Text(
        text = title,
        color = Gray,
        lineHeight = 1.4.em,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.fillMaxWidth()
    )

    Text(
        text = body,
        color = DarkGray,
        lineHeight = 1.4.em,
        style = TextStyle(
            fontSize = 12.sp
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
    if (postedDate.isNotEmpty()) {
        Text(
            text = "Posted on $postedDate",
            color = Gray,
            lineHeight = 1.em,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }

}

@Preview(
    showBackground = true
)
@Composable
private fun NavigatorDetailScreenPreview() {
    LokalJobsTheme {
        DetailScreen(getDummyJob())
    }
}