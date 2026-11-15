package com.ejemplo.librodragones.ui.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ejemplo.librodragones.R
import com.ejemplo.librodragones.model.Dragon
import com.ejemplo.librodragones.model.Encuentro
import com.ejemplo.librodragones.model.estadisticas
import com.ejemplo.librodragones.ui.componentes.BarraInferior
import com.ejemplo.librodragones.ui.componentes.CabeceraLibro
import com.ejemplo.librodragones.ui.componentes.FilaEstadisticas
import com.ejemplo.librodragones.ui.componentes.ItemEncuentro
import com.ejemplo.librodragones.ui.componentes.PaginaPergamino
import com.ejemplo.librodragones.ui.componentes.Pestana
import com.ejemplo.librodragones.ui.componentes.coloresCampo
import com.ejemplo.librodragones.ui.theme.Marron
import com.ejemplo.librodragones.ui.theme.MarronOscuro
import com.ejemplo.librodragones.ui.theme.PergaminoClaro
import com.ejemplo.librodragones.ui.theme.PergaminoOscuro
import com.ejemplo.librodragones.ui.theme.RojoSello
import com.ejemplo.librodragones.ui.theme.VerdeBosque


@Composable
fun RegistroScreen(
    dragones: List<Dragon>,
    encuentros: List<Encuentro>,
    onAnadir: () -> Unit,
    onEliminar: (Set<Int>) -> Unit,
    onEncuentroClick: (Encuentro) -> Unit,
    onIrAEnciclopedia: () -> Unit
) {
    val focusManager = LocalFocusManager.current


    var consulta by remember { mutableStateOf("") }
    var dragonFiltrado by remember { mutableStateOf<String?>(null) }
    var buscadorActivo by remember { mutableStateOf(false) }
    var modoBorrado by remember { mutableStateOf(false) }
    var seleccionados by remember { mutableStateOf(setOf<Int>()) }
    var mostrarDialogo by remember { mutableStateOf(false) }

    val encuentrosVisibles = encuentros
        .filter { enc ->
            dragonFiltrado == null || dragones.firstOrNull { it.id == enc.dragonId }?.nombre == dragonFiltrado
        }
        .sortedByDescending { it.fecha }

    val sugerencias = dragones.map { it.nombre }.filter { it.startsWith(consulta.trim(), ignoreCase = true) }

    PaginaPergamino(
        bottomBar = {
            BarraInferior(
                actual = Pestana.REGISTRO,
                onEnciclopedia = onIrAEnciclopedia,
                onRegistro = {}
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                CabeceraLibro(
                    titulo = stringResource(R.string.titulo_registro),
                    subtitulo = stringResource(R.string.subtitulo_registro)
                )
                FilaEstadisticas(encuentros.estadisticas())

                OutlinedTextField(
                    value = consulta,
                    onValueChange = {
                        consulta = it
                        dragonFiltrado = null
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .onFocusChanged { buscadorActivo = it.isFocused },
                    placeholder = { Text(stringResource(R.string.busqueda_hint)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (consulta.isNotEmpty()) {
                            IconButton(onClick = {
                                consulta = ""
                                dragonFiltrado = null
                            }) {
                                Icon(Icons.Default.Close, contentDescription = "Borrar búsqueda")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),
                    colors = coloresCampo()
                )

                if (buscadorActivo) {

                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        if (sugerencias.isEmpty()) {
                            item {
                                Text(
                                    text = "Ningún dragón empieza por \"$consulta\".",
                                    modifier = Modifier.padding(12.dp),
                                    color = Marron
                                )
                            }
                        }
                        items(sugerencias) { nombre ->
                            Text(
                                text = nombre,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        consulta = nombre
                                        dragonFiltrado = nombre
                                        focusManager.clearFocus()
                                    }
                                    .padding(horizontal = 12.dp, vertical = 14.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MarronOscuro
                            )
                        }
                    }
                } else if (encuentrosVisibles.isEmpty()) {
                    Text(
                        text = if (encuentros.isEmpty())
                            "Aún no hay encuentros anotados. Pulsa «Añadir» para escribir el primero."
                        else
                            "No hay encuentros con ese dragón.",
                        modifier = Modifier.padding(top = 24.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Marron
                    )
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(bottom = 96.dp)
                    ) {
                        items(encuentrosVisibles, key = { it.id }) { encuentro ->
                            val dragon = dragones.firstOrNull { it.id == encuentro.dragonId }
                            if (dragon != null) {
                                ItemEncuentro(
                                    encuentro = encuentro,
                                    dragon = dragon,
                                    onClick = { onEncuentroClick(encuentro) },
                                    modoBorrado = modoBorrado,
                                    seleccionado = encuentro.id in seleccionados,
                                    onSeleccionChange = { marcado ->
                                        seleccionados = if (marcado) {
                                            seleccionados + encuentro.id
                                        } else {
                                            seleccionados - encuentro.id
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }


            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ExtendedFloatingActionButton(
                    onClick = { if (!modoBorrado) onAnadir() },
                    containerColor = if (modoBorrado) PergaminoOscuro else VerdeBosque,
                    contentColor = if (modoBorrado) Marron.copy(alpha = 0.5f) else PergaminoClaro,
                    icon = { Icon(Icons.Default.Add, contentDescription = null) },
                    text = { Text("Añadir") }
                )
                ExtendedFloatingActionButton(
                    onClick = {
                        if (!modoBorrado) {
                            modoBorrado = true
                        } else if (seleccionados.isEmpty()) {
                            modoBorrado = false
                        } else {
                            mostrarDialogo = true
                        }
                    },
                    containerColor = RojoSello,
                    contentColor = PergaminoClaro,
                    icon = { Icon(Icons.Default.Delete, contentDescription = null) },
                    text = {
                        Text(if (modoBorrado) "Confirmar (${seleccionados.size})" else "Eliminar")
                    }
                )
            }
        }
    }

    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            title = { Text("Eliminar encuentros") },
            text = {
                Text("Se van a borrar ${seleccionados.size} encuentro(s) del registro. Esta acción no se puede deshacer.")
            },
            confirmButton = {
                TextButton(onClick = {

                    val aBorrar = encuentrosVisibles.map { it.id }.filter { it in seleccionados }.toSet()
                    onEliminar(aBorrar)
                    seleccionados = emptySet()
                    modoBorrado = false
                    mostrarDialogo = false
                }) { Text("Aceptar") }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogo = false }) { Text("Cancelar") }
            }
        )
    }
}
