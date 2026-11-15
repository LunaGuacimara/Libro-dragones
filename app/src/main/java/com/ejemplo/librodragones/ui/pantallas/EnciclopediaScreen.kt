package com.ejemplo.librodragones.ui.pantallas

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ejemplo.librodragones.R
import com.ejemplo.librodragones.model.Dragon
import com.ejemplo.librodragones.ui.componentes.BarraInferior
import com.ejemplo.librodragones.ui.componentes.CabeceraLibro
import com.ejemplo.librodragones.ui.componentes.PaginaPergamino
import com.ejemplo.librodragones.ui.componentes.Pestana
import com.ejemplo.librodragones.ui.componentes.TarjetaDragon
import com.ejemplo.librodragones.ui.componentes.coloresCampo
import com.ejemplo.librodragones.ui.theme.Marron
import com.ejemplo.librodragones.ui.theme.MarronOscuro
import com.ejemplo.librodragones.ui.theme.PergaminoClaro
import com.ejemplo.librodragones.ui.theme.VerdeBosque

@Composable
fun EnciclopediaScreen(
    dragones: List<Dragon>,
    onDragonClick: (Dragon) -> Unit,
    onIrARegistro: () -> Unit
) {
    var consulta by remember { mutableStateOf("") }
    var claseSeleccionada by remember { mutableStateOf<String?>(null) }

    val clases = dragones.map { it.clase }.distinct()

    val dragonesVisibles = dragones.filter { dragon ->
        val coincideClase = claseSeleccionada == null || dragon.clase == claseSeleccionada
        val texto = consulta.trim()
        val coincideTexto = texto.isEmpty() ||
                dragon.nombre.contains(texto, ignoreCase = true) ||
                dragon.clase.contains(texto, ignoreCase = true)
        coincideClase && coincideTexto
    }

    PaginaPergamino(
        bottomBar = {
            BarraInferior(
                actual = Pestana.ENCICLOPEDIA,
                onEnciclopedia = {},
                onRegistro = onIrARegistro
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            CabeceraLibro(
                titulo = stringResource(R.string.titulo_enciclopedia),
                subtitulo = stringResource(R.string.subtitulo_enciclopedia)
            )


            OutlinedTextField(
                value = consulta,
                onValueChange = { consulta = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Busca por nombre o clase") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (consulta.isNotEmpty()) {
                        IconButton(onClick = { consulta = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Borrar búsqueda")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                colors = coloresCampo()
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                clases.forEach { clase ->
                    FilterChip(
                        selected = claseSeleccionada == clase,
                        onClick = {
                            claseSeleccionada = if (claseSeleccionada == clase) null else clase
                        },
                        label = { Text(clase) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = PergaminoClaro,
                            labelColor = MarronOscuro,
                            selectedContainerColor = VerdeBosque,
                            selectedLabelColor = PergaminoClaro
                        )
                    )
                }
            }

            if (dragonesVisibles.isEmpty()) {
                Text(
                    text = "Ningún dragón coincide con la búsqueda.",
                    modifier = Modifier.padding(top = 16.dp),
                    color = Marron
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(dragonesVisibles, key = { it.id }) { dragon ->
                    TarjetaDragon(dragon = dragon, onClick = { onDragonClick(dragon) })
                }
            }
        }
    }
}
