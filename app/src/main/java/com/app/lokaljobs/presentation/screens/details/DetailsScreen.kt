package com.app.lokaljobs.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
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
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.presentation.formattedSalary
import com.app.lokaljobs.presentation.getDummyJob
import com.app.lokaljobs.presentation.getTime
import com.app.lokaljobs.presentation.screens.details.components.ButtonData
import com.app.lokaljobs.presentation.screens.details.components.ButtonGroup
import com.app.lokaljobs.presentation.screens.details.components.Summary
import com.app.lokaljobs.presentation.screens.details.components.SummaryItemHolder
import com.app.lokaljobs.ui.theme.DarkGray
import com.app.lokaljobs.ui.theme.Gray
import com.app.lokaljobs.ui.theme.Highlight
import com.app.lokaljobs.ui.theme.LokalJobsTheme
import com.app.lokaljobs.ui.theme.LokalTypography.body1Lokal
import com.app.lokaljobs.ui.theme.LokalTypography.heading2Lokal
import com.app.lokaljobs.ui.theme.OnHighlight
import com.app.lokaljobs.ui.theme.OnHighlightDark
import com.cinderella.lokaljobs.R

@Composable
fun DetailScreen(job: JobEntity) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Header
            Column(
                verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Highlight)
                    .heightIn(150.dp)
                    .padding(horizontal = 16.dp, vertical = 32.dp)
            ) {
                Text(
                    text = job.companyName,
                    color = OnHighlightDark,
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 16.sp, fontWeight = FontWeight.ExtraBold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = job.category,
                    color = OnHighlightDark,
                    lineHeight = 1.em,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Summary Block
            Summary(
                Modifier.padding(horizontal = 16.dp),
                listOf(
                    SummaryItemHolder(R.drawable.rupee, "Salary", job.formattedSalary()),
                    SummaryItemHolder(R.drawable.person, "Vacancy", "${job.openingCount}"),
                    SummaryItemHolder(R.drawable.call, "Contact", "HR"),
                    SummaryItemHolder(R.drawable.place, "Location", job.location),
                    SummaryItemHolder(R.drawable.fees, "Fees", "None"),
                    SummaryItemHolder(R.drawable.work_history, "Experience", job.experience),
                )
            )
            JobDescription(job.title, job.otherDetails ?: "", job.getTime() ?: "")
        }
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


@Composable
private fun JobDescription(title: String, body: String, postedDate: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Top),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Text(
            text = "Job description",
            color = Gray,
            style = heading2Lokal,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 9.dp)
        )

        Text(
            text = "$title\n\n$body",
            color = DarkGray,
            style = body1Lokal,
            modifier = Modifier.fillMaxWidth()
        )
        if (postedDate.isNotEmpty()) {
            Text(
                text = "Posted on $postedDate",
                color = Gray,
                style = body1Lokal
            )
        }
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun NavigatorDetailScreenPreview() {
    LokalJobsTheme {
        DetailScreen(job = getDummyJob())
    }
}