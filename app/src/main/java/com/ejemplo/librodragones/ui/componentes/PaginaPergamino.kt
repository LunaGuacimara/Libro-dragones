package com.ejemplo.librodragones.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.ejemplo.librodragones.ui.theme.Marron
import com.ejemplo.librodragones.ui.theme.Pergamino
import com.ejemplo.librodragones.ui.theme.PergaminoClaro
import com.ejemplo.librodragones.ui.theme.PergaminoOscuro


@Composable
fun PaginaPergamino(
    modifier: Modifier = Modifier,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(PergaminoClaro, Pergamino, PergaminoOscuro)))
            .drawBehind {
                val margen = 10.dp.toPx()
                drawRect(
                    color = Marron.copy(alpha = 0.25f),
                    topLeft = Offset(margen, margen),
                    size = Size(size.width - 2 * margen, size.height - 2 * margen),
                    style = Stroke(width = 1.dp.toPx())
                )
            }
    ) {
        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = bottomBar,
            content = content
        )
    }
}
