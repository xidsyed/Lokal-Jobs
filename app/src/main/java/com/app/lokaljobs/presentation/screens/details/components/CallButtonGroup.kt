package com.app.lokaljobs.presentation.screens.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.ui.theme.DarkGray
import com.app.lokaljobs.ui.theme.Green
import com.app.lokaljobs.ui.theme.OnHighlight
import com.cinderella.lokaljobs.R

@Composable
fun CallButtonGroup() {
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
