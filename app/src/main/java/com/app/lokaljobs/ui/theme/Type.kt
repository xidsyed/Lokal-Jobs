package com.app.lokaljobs.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cinderella.lokaljobs.R


val Roboto = FontFamily(
    Font(R.font.roboto, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(fontFamily = Roboto),
    displayMedium = TextStyle(fontFamily = Roboto),
    displaySmall = TextStyle(fontFamily = Roboto),
    headlineLarge = TextStyle(fontFamily = Roboto),
    headlineMedium = TextStyle(fontFamily = Roboto),
    headlineSmall = TextStyle(fontFamily = Roboto),
    titleLarge = TextStyle(fontFamily = Roboto),
    titleMedium = TextStyle(fontFamily = Roboto),
    titleSmall = TextStyle(fontFamily = Roboto),
    bodyMedium = TextStyle(fontFamily = Roboto),
    bodySmall = TextStyle(fontFamily = Roboto),
    labelLarge = TextStyle(fontFamily = Roboto),
    labelMedium = TextStyle(fontFamily = Roboto),
    labelSmall = TextStyle(fontFamily = Roboto)
)

object LokalTypography {
    val subtitle1Lokal = TextStyle(
        lineHeight = 1.em,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        color = DarkGray
    )
    val subtitle2Lokal = TextStyle(
        lineHeight = 1.em,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = DarkGray
    )
    val heading2Lokal = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 1.em,
        color = DarkGray
    )
    val body1Lokal = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        color = DarkGray
    )

    val button1Lokal = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 1.em,
        color = OnHighlightDark
    )


}