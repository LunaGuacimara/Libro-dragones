package com.ejemplo.librodragones.ui.pantallas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ejemplo.librodragones.model.Dragon
import com.ejemplo.librodragones.model.Encuentro
import com.ejemplo.librodragones.model.estadisticas
import com.ejemplo.librodragones.model.formatoCorto
import com.ejemplo.librodragones.ui.componentes.CabeceraLibro
import com.ejemplo.librodragones.ui.componentes.CajaTexto
import com.ejemplo.librodragones.ui.componentes.FilaEstadisticas
import com.ejemplo.librodragones.ui.componentes.ImagenDragon
import com.ejemplo.librodragones.ui.componentes.IndicadorPeligrosidad
import com.ejemplo.librodragones.ui.componentes.PaginaPergamino
import com.ejemplo.librodragones.ui.componentes.TituloSeccion
import com.ejemplo.librodragones.ui.theme.Marron
import com.ejemplo.librodragones.ui.theme.MarronOscuro
import com.ejemplo.librodragones.ui.theme.Oro

@Composable
fun DetalleEncuentroScreen(
    encuentro: Encuentro,
    dragon: Dragon,
    encuentrosDelDragon: List<Encuentro>,
    onBack: () -> Unit
) {
    PaginaPergamino { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            CabeceraLibro(titulo = dragon.nombre, subtitulo = dragon.clase, onBack = onBack)

            ImagenDragon(
                nombreRecurso = dragon.imagen,
                descripcion = dragon.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.3f)
                    .clip(RoundedCornerShape(6.dp))
                    .border(BorderStroke(2.dp, Oro), RoundedCornerShape(6.dp))
            )

            TituloSeccion("Encuentro")
            DatoFila("Lugar", encuentro.lugar)
            DatoFila("Fecha", encuentro.fecha.formatoCorto())
            IndicadorPeligrosidad(
                peligrosidad = encuentro.peligrosidad,
                modifier = Modifier.padding(top = 6.dp)
            )

            TituloSeccion("Habilidades observadas")
            if (encuentro.habilidadesObservadas.isEmpty()) {
                Text("Ninguna anotada.", color = Marron)
            } else {
                encuentro.habilidadesObservadas.forEach { habilidad ->
                    Text(
                        text = "• $habilidad",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MarronOscuro
                    )
                }
            }

            TituloSeccion("Observaciones")
            CajaTexto(encuentro.observaciones.ifBlank { "Sin observaciones." })

            TituloSeccion("Historial con esta especie")
            FilaEstadisticas(encuentrosDelDragon.estadisticas())

            TituloSeccion("Sobre ${dragon.nombre}")
            CajaTexto(dragon.descripcion, modifier = Modifier.padding(bottom = 24.dp))
        }
    }
}

@Composable
private fun DatoFila(etiqueta: String, valor: String) {
    Text(
        text = "$etiqueta: $valor",
        style = MaterialTheme.typography.bodyLarge,
        color = MarronOscuro,
        fontWeight = FontWeight.Normal
    )
}
