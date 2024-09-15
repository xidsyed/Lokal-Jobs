package com.app.lokaljobs.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.data.local.JobEntity
import com.app.lokaljobs.presentation.getDummyJob
import com.app.lokaljobs.presentation.getTime
import com.app.lokaljobs.presentation.screens.details.components.CallButtonGroup
import com.app.lokaljobs.presentation.screens.details.components.Summary
import com.app.lokaljobs.ui.theme.DarkGray
import com.app.lokaljobs.ui.theme.Gray
import com.app.lokaljobs.ui.theme.LokalJobsTheme

@Composable
fun DetailScreen(modifier: Modifier = Modifier, job: JobEntity) {
    Column(
        modifier = modifier
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
        Summary(job)

        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            JobDescription(job.title, job.otherDetails ?: "", job.getTime() ?: "")
            CallButtonGroup()
        }
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
        DetailScreen(job = getDummyJob())
    }
}