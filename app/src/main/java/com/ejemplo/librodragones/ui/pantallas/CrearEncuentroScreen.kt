package com.ejemplo.librodragones.ui.pantallas

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ejemplo.librodragones.model.Dragon
import com.ejemplo.librodragones.model.Encuentro
import com.ejemplo.librodragones.ui.componentes.CabeceraLibro
import com.ejemplo.librodragones.ui.componentes.CampoDesplegable
import com.ejemplo.librodragones.ui.componentes.PaginaPergamino
import com.ejemplo.librodragones.ui.componentes.SelectorFecha
import com.ejemplo.librodragones.ui.componentes.TituloSeccion
import com.ejemplo.librodragones.ui.componentes.coloresCampo
import com.ejemplo.librodragones.ui.componentes.colorPeligro
import com.ejemplo.librodragones.ui.theme.Marron
import com.ejemplo.librodragones.ui.theme.MarronOscuro
import com.ejemplo.librodragones.ui.theme.PergaminoClaro
import com.ejemplo.librodragones.ui.theme.PergaminoOscuro
import com.ejemplo.librodragones.ui.theme.VerdeBosque
import java.time.LocalDate
import kotlin.math.roundToInt


@Composable
fun CrearEncuentroScreen(
    dragones: List<Dragon>,
    onGuardar: (Encuentro) -> Unit,
    onDescartar: () -> Unit
) {
    var dragonId by remember { mutableStateOf<Int?>(null) }
    var lugar by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf(LocalDate.now()) }
    var peligrosidad by remember { mutableFloatStateOf(0f) }
    val habilidadesMarcadas = remember { mutableStateListOf<String>() }
    var observaciones by remember { mutableStateOf("") }

    val dragon = dragones.firstOrNull { it.id == dragonId }

    fun guardarYVolver() {
        if (dragon == null) {
            onDescartar()
            return
        }
        onGuardar(
            Encuentro(
                id = 0,
                dragonId = dragon.id,
                lugar = lugar.trim().ifBlank { "Lugar desconocido" },
                fecha = fecha,
                peligrosidad = peligrosidad.roundToInt(),
                habilidadesObservadas = habilidadesMarcadas.toList(),
                observaciones = observaciones.trim()
            )
        )
    }

    BackHandler { guardarYVolver() }

    PaginaPergamino { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {
            CabeceraLibro(
                titulo = "Nuevo encuentro",
                subtitulo = "Al volver atrás se guarda la anotación",
                onBack = { guardarYVolver() }
            )


            TituloSeccion("Dragón encontrado:")
            CampoDesplegable(
                etiqueta = "Especie",
                valor = dragon?.nombre ?: "",
                opciones = dragones.map { it.nombre },
                onSeleccion = { nombre ->
                    val elegido = dragones.firstOrNull { it.nombre == nombre }
                    if (elegido?.id != dragonId) {
                        habilidadesMarcadas.clear() // las habilidades dependen de la especie
                    }
                    dragonId = elegido?.id
                }
            )


            TituloSeccion("Lugar del encuentro:")
            OutlinedTextField(
                value = lugar,
                onValueChange = { lugar = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Lugar") },
                singleLine = true,
                colors = coloresCampo()
            )


            TituloSeccion("Fecha:")
            SelectorFecha(fecha = fecha, onFechaChange = { fecha = it })


            TituloSeccion("Nivel de peligrosidad (0 a 100):")
            Row(verticalAlignment = Alignment.CenterVertically) {
                Slider(
                    value = peligrosidad,
                    onValueChange = { peligrosidad = it },
                    modifier = Modifier.weight(1f),
                    valueRange = 0f..100f,
                    steps = 99,
                    colors = SliderDefaults.colors(
                        thumbColor = colorPeligro(peligrosidad.roundToInt()),
                        activeTrackColor = colorPeligro(peligrosidad.roundToInt()),
                        inactiveTrackColor = PergaminoOscuro,
                        activeTickColor = Color.Transparent,
                        inactiveTickColor = Color.Transparent
                    )
                )
                Text(
                    text = peligrosidad.roundToInt().toString(),
                    modifier = Modifier.width(40.dp),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MarronOscuro
                )
            }


            TituloSeccion("Habilidades observadas:")
            if (dragon == null) {
                Text("Elige primero un dragón.", color = Marron)
            } else if (dragon.habilidades.isEmpty()) {
                Text("Este dragón no tiene habilidades en el catálogo.", color = Marron)
            } else {
                dragon.habilidades.forEach { habilidad ->
                    val marcada = habilidad in habilidadesMarcadas
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (marcada) {
                                    habilidadesMarcadas.remove(habilidad)
                                } else {
                                    habilidadesMarcadas.add(habilidad)
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = marcada,
                            onCheckedChange = null,
                            modifier = Modifier.padding(12.dp),
                            colors = CheckboxDefaults.colors(
                                checkedColor = VerdeBosque,
                                checkmarkColor = PergaminoClaro,
                                uncheckedColor = Marron
                            )
                        )
                        Text(
                            text = habilidad,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MarronOscuro
                        )
                    }
                }
            }


            TituloSeccion("Observaciones adicionales:")
            OutlinedTextField(
                value = observaciones,
                onValueChange = { observaciones = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Notas personales") },
                minLines = 3,
                colors = coloresCampo()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onDescartar,
                    modifier = Modifier.weight(1f)
                ) { Text("Descartar", color = Marron) }
                Button(
                    onClick = { guardarYVolver() },
                    enabled = dragon != null,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeBosque,
                        contentColor = PergaminoClaro
                    )
                ) { Text("Guardar") }
            }
        }
    }
}
