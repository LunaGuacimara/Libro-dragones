package com.ejemplo.librodragones.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.ejemplo.librodragones.ui.theme.PergaminoOscuro


@Composable
fun ImagenDragon(
    nombreRecurso: String,
    descripcion: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    val context = LocalContext.current
    val recursoId = remember(nombreRecurso) {
        context.resources.getIdentifier(nombreRecurso, "drawable", context.packageName)
    }

    if (recursoId != 0) {
        Image(
            painter = painterResource(id = recursoId),
            contentDescription = descripcion,
            modifier = modifier,
            contentScale = ContentScale.Fit
        )
    } else {
        Box(
            modifier = modifier.background(PergaminoOscuro),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "🐉", fontSize = 32.sp)
        }
    }
}
