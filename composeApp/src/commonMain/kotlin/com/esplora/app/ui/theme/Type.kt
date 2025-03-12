package com.esplora.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import esplora.composeapp.generated.resources.Res
import esplora.composeapp.generated.resources.montserrat_bold
import esplora.composeapp.generated.resources.montserrat_bolditalic
import esplora.composeapp.generated.resources.montserrat_italic
import esplora.composeapp.generated.resources.montserrat_light
import esplora.composeapp.generated.resources.montserrat_regular
import org.jetbrains.compose.resources.Font

@Composable
fun EsploraFontFamily() = FontFamily(
    Font(Res.font.montserrat_light, weight = FontWeight.Light),
    Font(Res.font.montserrat_regular, weight = FontWeight.Normal),
    Font(Res.font.montserrat_italic, style = FontStyle.Italic),
    Font(Res.font.montserrat_bolditalic, style = FontStyle.Italic, weight = FontWeight.Bold),
    Font(Res.font.montserrat_bold, weight = FontWeight.Bold)
)

@Composable
fun Typography() = Typography().run {

    val fontFamily = EsploraFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}

