package com.ejemplo.librodragones.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ejemplo.librodragones.ui.theme.Marron
import com.ejemplo.librodragones.ui.theme.MarronOscuro
import com.ejemplo.librodragones.ui.theme.Oro
import com.ejemplo.librodragones.ui.theme.PergaminoClaro
import com.ejemplo.librodragones.ui.theme.PergaminoOscuro
import com.ejemplo.librodragones.ui.theme.VerdeBosque

enum class Pestana { ENCICLOPEDIA, REGISTRO }


@Composable
fun BarraInferior(
    actual: Pestana,
    onEnciclopedia: () -> Unit,
    onRegistro: () -> Unit
) {
    val colores = NavigationBarItemDefaults.colors(
        selectedIconColor = PergaminoClaro,
        selectedTextColor = MarronOscuro,
        indicatorColor = VerdeBosque,
        unselectedIconColor = Marron,
        unselectedTextColor = Marron
    )
    Column {
        HorizontalDivider(color = Oro)
        NavigationBar(containerColor = PergaminoOscuro) {
            NavigationBarItem(
                selected = actual == Pestana.ENCICLOPEDIA,
                onClick = onEnciclopedia,
                icon = { Icon(Icons.Default.Info, contentDescription = null) },
                label = { Text("Enciclopedia") },
                colors = colores
            )
            NavigationBarItem(
                selected = actual == Pestana.REGISTRO,
                onClick = onRegistro,
                icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                label = { Text("Registro") },
                colors = colores
            )
        }
    }
}
