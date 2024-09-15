package com.app.lokaljobs.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.presentation.Route
import com.app.lokaljobs.ui.theme.Accent
import com.app.lokaljobs.ui.theme.DarkGray
import com.cinderella.lokaljobs.R


@Composable
fun NavigatorTopBar(route: Route) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 28.dp)
            .fillMaxWidth()
    ) {
        when(route) {
            is Route.HomeScreen -> {
                Image(
                    painter = painterResource(id = R.drawable.lokal_logo),
                    contentDescription = "Lokal Icon",
                    modifier = Modifier.requiredSize(size = 30.dp)
                )
                Text(lineHeight = 1.sp, text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = DarkGray, fontSize = 36.sp, fontWeight = FontWeight.ExtraBold
                        )
                    ) { append("Lokal ") }
                    withStyle(
                        style = SpanStyle(
                            color = Accent, fontSize = 36.sp, fontWeight = FontWeight.ExtraBold
                        )
                    ) { append("Jobs") }
                })
            }
            else  -> {
                Text(
                    text = "Bookmarks",
                    modifier = Modifier.fillMaxWidth(),
                    color = DarkGray,
                    lineHeight = 1.sp,
                    style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 36.sp, color = DarkGray)
                )
            }

        }
    }
}

@Preview
@Composable
private fun TopAppBarPreview() {
    NavigatorTopBar(Route.BookmarkScreen)
}