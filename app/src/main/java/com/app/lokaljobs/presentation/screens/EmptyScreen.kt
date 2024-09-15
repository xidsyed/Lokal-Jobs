package com.app.lokaljobs.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.lokaljobs.ui.theme.Gray
import com.cinderella.lokaljobs.R

@Composable
fun EmptyScreen(message: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(300.dp),
            painter = painterResource(R.drawable.empty),
            contentDescription = "Empty Page"
        )
        if (message != null) {
            Text(
                modifier = Modifier.padding(32.dp),
                text = message,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.ExtraBold),
                color = Gray
            )
        }
    }
}
