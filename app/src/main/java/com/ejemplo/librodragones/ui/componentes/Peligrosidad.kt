package com.ejemplo.librodragones.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ejemplo.librodragones.model.nivelPeligro
import com.ejemplo.librodragones.ui.theme.MarronOscuro
import com.ejemplo.librodragones.ui.theme.Naranja
import com.ejemplo.librodragones.ui.theme.Ocre
import com.ejemplo.librodragones.ui.theme.PergaminoOscuro
import com.ejemplo.librodragones.ui.theme.RojoSello
import com.ejemplo.librodragones.ui.theme.VerdeMusgo

fun colorPeligro(peligrosidad: Int): Color = when {
    peligrosidad < 25 -> VerdeMusgo
    peligrosidad < 50 -> Ocre
    peligrosidad < 75 -> Naranja
    else -> RojoSello
}
@Composable
fun IndicadorPeligrosidad(peligrosidad: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Peligrosidad: $peligrosidad/100",
                style = MaterialTheme.typography.bodyMedium,
                color = MarronOscuro
            )
            Text(
                text = nivelPeligro(peligrosidad),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = colorPeligro(peligrosidad)
            )
        }
        LinearProgressIndicator(
            progress = { peligrosidad / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = colorPeligro(peligrosidad),
            trackColor = PergaminoOscuro
        )
    }
}
