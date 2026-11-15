package com.ejemplo.librodragones.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily

private val Base = Typography()

/** Toda la tipografía en serif para dar aire de libro antiguo. (Para una fuente propia, cambia FontFamily.Serif.) */
private val Letra = FontFamily.Serif

val TipografiaLibro = Typography(
    displayLarge = Base.displayLarge.copy(fontFamily = Letra),
    displayMedium = Base.displayMedium.copy(fontFamily = Letra),
    displaySmall = Base.displaySmall.copy(fontFamily = Letra),
    headlineLarge = Base.headlineLarge.copy(fontFamily = Letra),
    headlineMedium = Base.headlineMedium.copy(fontFamily = Letra),
    headlineSmall = Base.headlineSmall.copy(fontFamily = Letra),
    titleLarge = Base.titleLarge.copy(fontFamily = Letra),
    titleMedium = Base.titleMedium.copy(fontFamily = Letra),
    titleSmall = Base.titleSmall.copy(fontFamily = Letra),
    bodyLarge = Base.bodyLarge.copy(fontFamily = Letra),
    bodyMedium = Base.bodyMedium.copy(fontFamily = Letra),
    bodySmall = Base.bodySmall.copy(fontFamily = Letra),
    labelLarge = Base.labelLarge.copy(fontFamily = Letra),
    labelMedium = Base.labelMedium.copy(fontFamily = Letra),
    labelSmall = Base.labelSmall.copy(fontFamily = Letra)
)
