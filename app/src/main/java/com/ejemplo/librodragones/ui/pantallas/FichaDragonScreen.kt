package com.ejemplo.librodragones.ui.pantallas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ejemplo.librodragones.model.Dragon
import com.ejemplo.librodragones.model.Encuentro
import com.ejemplo.librodragones.model.estadisticas
import com.ejemplo.librodragones.ui.componentes.CabeceraLibro
import com.ejemplo.librodragones.ui.componentes.CajaTexto
import com.ejemplo.librodragones.ui.componentes.FilaEstadisticas
import com.ejemplo.librodragones.ui.componentes.ImagenDragon
import com.ejemplo.librodragones.ui.componentes.ItemEncuentro
import com.ejemplo.librodragones.ui.componentes.PaginaPergamino
import com.ejemplo.librodragones.ui.componentes.TituloSeccion
import com.ejemplo.librodragones.ui.theme.Marron
import com.ejemplo.librodragones.ui.theme.MarronOscuro
import com.ejemplo.librodragones.ui.theme.Oro


@Composable
fun FichaDragonScreen(
    dragon: Dragon,
    encuentros: List<Encuentro>,
    onBack: () -> Unit,
    onEncuentroClick: (Encuentro) -> Unit
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

            TituloSeccion("Habilidades")
            if (dragon.habilidades.isEmpty()) {
                Text("Sin habilidades registradas.", color = Marron)
            } else {
                dragon.habilidades.forEach { habilidad ->
                    Text(
                        text = "• $habilidad",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MarronOscuro
                    )
                }
            }

            TituloSeccion("Descripción")
            CajaTexto(dragon.descripcion)

            TituloSeccion("Mis encuentros con esta especie")
            FilaEstadisticas(encuentros.estadisticas())

            if (encuentros.isEmpty()) {
                Text(
                    text = "Todavía no has registrado ningún encuentro con este dragón.",
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Marron
                )
            } else {
                Column(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    encuentros.sortedByDescending { it.fecha }.forEach { encuentro ->
                        ItemEncuentro(
                            encuentro = encuentro,
                            dragon = dragon,
                            onClick = { onEncuentroClick(encuentro) }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
