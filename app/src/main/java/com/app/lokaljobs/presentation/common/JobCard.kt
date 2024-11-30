package com.app.lokaljobs.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.data.local.model.JobEntity
import com.app.lokaljobs.presentation.getDummyJob
import com.app.lokaljobs.presentation.getTime
import com.app.lokaljobs.ui.theme.DarkGray
import com.app.lokaljobs.ui.theme.Highlight
import com.app.lokaljobs.ui.theme.LightGray
import com.app.lokaljobs.ui.theme.LighterGray
import com.app.lokaljobs.ui.theme.LokalTypography.subtitle1Lokal
import com.app.lokaljobs.ui.theme.LokalTypography.subtitle2Lokal
import com.app.lokaljobs.ui.theme.OnHighlight
import com.app.lokaljobs.ui.theme.OnHighlightDark
import com.cinderella.lokaljobs.R

@Composable
fun JobCard(
    modifier: Modifier = Modifier,
    job: JobEntity,
    isBookmarked: Boolean,
    isHighlighted: Boolean = false,
    onJobCardClick: (JobEntity) -> Unit,
    onBookmarkIconClick: (JobEntity) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = if (isHighlighted) Highlight else Color.Transparent)
            .clickable { onJobCardClick(job) }
            .run {
                if (!isHighlighted)
                    border(
                        width = 0.5.dp,
                        color = LighterGray.copy(alpha = 0.25f),
                        shape = RoundedCornerShape(12.dp)
                    )
                else this
            }
            .padding(horizontal = 16.dp, vertical = 22.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f, true)
            ) {
                CompanyIcon()
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(1f, true)
                ) {
                    Text(
                        text = job.category,
                        color = DarkGray,
                        lineHeight = 1.em,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = job.companyName,
                            maxLines = 1,
                            style = subtitle1Lokal,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f, false)
                        )
                        Box(
                            modifier = Modifier
                                .requiredSize(size = 2.dp)
                                .clip(shape = CircleShape)
                                .background(color = DarkGray)
                        )
                        Text(
                            text = job.location,
                            color = DarkGray,
                            lineHeight = 1.em,
                            style = subtitle1Lokal
                        )
                    }
                }
            }
            Box(
                Modifier
                    .padding(start = 18.dp),
            ) {
                val tint = if (isBookmarked) {
                    if (isHighlighted) OnHighlightDark else OnHighlight
                } else {
                    if (isHighlighted) OnHighlight
                    else Color.LightGray
                }
                Icon(
                    painter = painterResource(
                        id = if (isBookmarked) R.drawable.bookmark_filled else R.drawable.bookmark_outline
                    ),
                    tint = tint,
                    contentDescription = "Bookmark",
                    modifier = Modifier
                        .requiredSize(size = 32.dp)
                        .clip(CircleShape)
                        .clickable { onBookmarkIconClick(job) }
                )
            }

        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(36.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = "Salary / Monthly",
                    color = DarkGray,
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 12.sp
                    ),
                )
                Text(
                    text = if (job.minSalary != null && job.maxSalary != null) "₹ ${job.minSalary}  -  ₹ ${job.maxSalary}"
                    else "Unspecified",
                    color = DarkGray,
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = "Vacancies",
                    color = DarkGray,
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_vacancy),
                        contentDescription = "Person book",
                        tint = DarkGray,
                        modifier = Modifier
                            .requiredSize(size = 16.dp)
                    )
                    Text(
                        text = job.openingCount.toString(),
                        color = DarkGray,
                        lineHeight = 1.em,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            BottomChip(R.drawable.work_history, job.experience, isHighlighted)
            job.whatsappNumber?.let {
                BottomChip(R.drawable.call, it, isHighlighted)
            }
            job.getTime()?.let {
                BottomChip(R.drawable.time_outline, it, isHighlighted)
            }
        }
    }
}

@Composable
fun CompanyIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredSize(size = 36.dp)
            .clip(shape = CircleShape)
            .background(color = Highlight)
            .padding(bottom = 1.dp, start = 1.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_place),
            tint = OnHighlightDark,
            contentDescription = "Icon",
            modifier = Modifier
        )
    }
}

@Composable
fun BottomChip(
    @DrawableRes resId: Int,
    text: String,
    isHighlighted: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = "Icon",
            tint = if (isHighlighted) DarkGray else LightGray,
            modifier = Modifier
                .requiredSize(size = 12.dp)
        )
        Text(
            text = text,
            color = if (isHighlighted) DarkGray else LightGray,
            lineHeight = 1.em,
            style = subtitle2Lokal
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xffffffff)
@Composable
private fun JobCardsPreview() {
    Column(modifier = Modifier.padding(4.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        JobCard(
            job = getDummyJob(),
            isBookmarked = true,
            onJobCardClick = {},
            onBookmarkIconClick = {})
        JobCard(
            job = getDummyJob(),
            isBookmarked = false,
            onJobCardClick = {},
            onBookmarkIconClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun JobCardHighlightedPreview() {
    Column(modifier = Modifier.padding(4.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        JobCard(
            job = getDummyJob(),
            isBookmarked = true,
            isHighlighted = true,
            onJobCardClick = {},
            onBookmarkIconClick = {}
        )
        JobCard(
            job = getDummyJob(),
            isBookmarked = false,
            isHighlighted = true,
            onJobCardClick = {},
            onBookmarkIconClick = {}
        )

    }
}