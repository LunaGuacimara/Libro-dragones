package com.ejemplo.librodragones.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ejemplo.librodragones.model.formatoCorto
import com.ejemplo.librodragones.ui.theme.Marron
import com.ejemplo.librodragones.ui.theme.PergaminoClaro
import com.ejemplo.librodragones.ui.theme.VerdeBosque
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset


@Composable
fun coloresCampo() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = PergaminoClaro,
    unfocusedContainerColor = PergaminoClaro,
    focusedBorderColor = VerdeBosque,
    unfocusedBorderColor = Marron,
    focusedLabelColor = VerdeBosque,
    unfocusedLabelColor = Marron,
    cursorColor = VerdeBosque
)


@Composable
fun CampoDesplegable(
    etiqueta: String,
    valor: String,
    opciones: List<String>,
    onSeleccion: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var abierto by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = valor,
            onValueChange = {},
            readOnly = true,
            label = { Text(etiqueta) },
            placeholder = { Text("Selecciona un valor") },
            trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            colors = coloresCampo()
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { abierto = true }
        )
        DropdownMenu(
            expanded = abierto,
            onDismissRequest = { abierto = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        onSeleccion(opcion)
                        abierto = false
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorFecha(
    fecha: LocalDate,
    onFechaChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    var mostrar by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = fecha.formatoCorto(),
            onValueChange = {},
            readOnly = true,
            label = { Text("Fecha del encuentro") },
            trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            colors = coloresCampo()
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { mostrar = true }
        )
    }

    if (mostrar) {
        val ahora = System.currentTimeMillis()
        val estado = rememberDatePickerState(
            initialSelectedDateMillis = fecha.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli(),
            selectableDates = object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean = utcTimeMillis <= ahora
            }
        )
        DatePickerDialog(
            onDismissRequest = { mostrar = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        estado.selectedDateMillis?.let { millis ->
                            onFechaChange(
                                Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate()
                            )
                        }
                        mostrar = false
                    }
                ) { Text("Aceptar") }
            },
            dismissButton = {
                TextButton(onClick = { mostrar = false }) { Text("Cancelar") }
            }
        ) {
            DatePicker(state = estado)
        }
    }
}
