package com.app.lokaljobs.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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
import com.app.lokaljobs.ui.theme.DarkGray
import com.app.lokaljobs.ui.theme.Highlight
import com.app.lokaljobs.ui.theme.HighlightOverlay
import com.app.lokaljobs.ui.theme.LightGray
import com.app.lokaljobs.ui.theme.OnHighlightDark
import com.app.lokaljobs.ui.theme.Surface
import com.cinderella.lokaljobs.R

@Composable
fun JobCard(
    job: JobEntity,
    isBookmarked: Boolean,
    isHighlighted: Boolean = false,
    onJobCardClick: (JobEntity) -> Unit,
    onBookmarkIconClick: (JobEntity) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = if (isHighlighted) Highlight else Surface)
            .clickable { onJobCardClick(job) }
            .padding(
                horizontal = 16.dp, vertical = 18.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompanyIcon()
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                ) {
                    Text(
                        text = job.category,
                        color = DarkGray,
                        lineHeight = 1.em,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = job.companyName,
                            color = DarkGray,
                            lineHeight = 1.em,
                            maxLines = 1,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f, false)
                        )
                        Box(
                            modifier = Modifier
                                .requiredSize(size = 2.dp)
                                .clip(shape = CircleShape)
                                .background(color = LightGray)
                        )
                        Text(
                            text = job.location,
                            color = DarkGray,
                            lineHeight = 1.em,
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }
            IconButton(
                onClick = { onBookmarkIconClick(job) },
                modifier = Modifier.padding(start = 18.dp),
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isBookmarked) R.drawable.bookmark_filled_dark
                        else R.drawable.bookmark_unselected_light_gray
                    ),
                    contentDescription = "Bookmark",
                    modifier = Modifier
                        .requiredSize(size = 32.dp)
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
                        fontSize = 10.sp
                    ),
                    modifier = Modifier
                        .requiredWidth(width = 76.dp)
                )
                Text(
                    text = if (job.minSalary != null && job.maxSalary != null) "₹ ${job.minSalary}  -  ₹ ${job.maxSalary}"
                    else "Unspecified",
                    color = DarkGray,
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .padding(end = 3.dp)
            ) {
                Text(
                    text = "Vacancies",
                    color = DarkGray,
                    lineHeight = 1.em,
                    style = TextStyle(
                        fontSize = 10.sp
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
                            .requiredSize(size = 14.dp)
                    )
                    Text(
                        text = job.openingCount.toString(),
                        color = DarkGray,
                        lineHeight = 1.em,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.Start),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
        ) {
            BottomChip(R.drawable.icon_experience, job.experience, isHighlighted)
            job.whatsappNumber?.let {
                BottomChip(R.drawable.icon_call, it, isHighlighted)
            }
            job.getTime()?.let {
                BottomChip(R.drawable.icon_time, it, isHighlighted)
            }
        }
    }
}

@Composable
fun CompanyIcon(modifier: Modifier = Modifier) {
    // TODO: get enum and display icon
    Box(
        modifier = modifier
            .requiredSize(size = 36.dp)
            .clip(shape = CircleShape)
            .background(color = HighlightOverlay),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_place),
            tint = OnHighlightDark,
            contentDescription = "Icon",
            modifier = Modifier
                .clip(shape = CircleShape)
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
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = "Component 2",
            tint = if (isHighlighted) OnHighlightDark else LightGray,
            modifier = Modifier
                .requiredSize(size = 10.dp)
        )
        Text(
            text = text,
            color = if (isHighlighted) OnHighlightDark else LightGray,
            lineHeight = 1.em,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            ),
        )
    }
}

@Preview()
@Composable
private fun JobCardsPreview() {
    JobCard(job = getDummyJob(), true, onJobCardClick = {}, onBookmarkIconClick = {})
}

@Preview()
@Composable
private fun JobCardHighlightedPreview() {
    JobCard(
        job = getDummyJob(),
        true,
        isHighlighted = true,
        onJobCardClick = {},
        onBookmarkIconClick = {})
}