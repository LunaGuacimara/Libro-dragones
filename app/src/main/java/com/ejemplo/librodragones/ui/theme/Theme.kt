package com.ejemplo.librodragones.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val EsquemaPergamino = lightColorScheme(
    primary = VerdeBosque,
    onPrimary = PergaminoClaro,
    primaryContainer = VerdeMusgo,
    onPrimaryContainer = PergaminoClaro,
    secondary = Marron,
    onSecondary = PergaminoClaro,
    secondaryContainer = PergaminoOscuro,
    onSecondaryContainer = MarronOscuro,
    tertiary = Oro,
    onTertiary = MarronOscuro,
    background = Pergamino,
    onBackground = MarronOscuro,
    surface = PergaminoClaro,
    onSurface = MarronOscuro,
    surfaceVariant = PergaminoOscuro,
    onSurfaceVariant = Marron,
    surfaceContainerLowest = PergaminoClaro,
    surfaceContainerLow = PergaminoClaro,
    surfaceContainer = PergaminoClaro,
    surfaceContainerHigh = PergaminoClaro,
    surfaceContainerHighest = PergaminoOscuro,
    outline = Marron,
    outlineVariant = Oro,
    error = RojoSello,
    onError = PergaminoClaro
)

/** Tema único (siempre claro): el aspecto de pergamino no cambia con el modo oscuro del móvil. */
@Composable
fun LibroDragonesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EsquemaPergamino,
        typography = TipografiaLibro,
        content = content
    )
}
