package com.ejemplo.librodragones.ui.componentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ejemplo.librodragones.model.Dragon
import com.ejemplo.librodragones.model.Encuentro
import com.ejemplo.librodragones.model.Estadisticas
import com.ejemplo.librodragones.model.formatoCorto
import com.ejemplo.librodragones.ui.theme.Marron
import com.ejemplo.librodragones.ui.theme.MarronOscuro
import com.ejemplo.librodragones.ui.theme.Oro
import com.ejemplo.librodragones.ui.theme.PergaminoClaro
import com.ejemplo.librodragones.ui.theme.PergaminoOscuro
import com.ejemplo.librodragones.ui.theme.VerdeBosque

private val FormaPagina = RoundedCornerShape(6.dp)

@Composable
fun TarjetaDragon(dragon: Dragon, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = FormaPagina,
        colors = CardDefaults.cardColors(
            containerColor = dragon.color.copy(alpha = 0.12f).compositeOver(PergaminoClaro)
        ),
        border = BorderStroke(1.dp, Marron.copy(alpha = 0.6f))
    ) {
        Column {
            ImagenDragon(
                nombreRecurso = dragon.imagen,
                descripcion = dragon.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(dragon.color)
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = dragon.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MarronOscuro,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = dragon.clase,
                    style = MaterialTheme.typography.bodySmall,
                    color = Marron
                )
            }
        }
    }
}


@Composable
fun ItemEncuentro(
    encuentro: Encuentro,
    dragon: Dragon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    modoBorrado: Boolean = false,
    seleccionado: Boolean = false,
    onSeleccionChange: (Boolean) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (modoBorrado) onSeleccionChange(!seleccionado) else onClick()
            },
        shape = FormaPagina,
        colors = CardDefaults.cardColors(
            containerColor = dragon.color.copy(alpha = 0.15f).compositeOver(PergaminoClaro)
        ),
        border = BorderStroke(1.dp, Marron.copy(alpha = 0.6f))
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .width(8.dp)
                    .fillMaxHeight()
                    .background(dragon.color)
            )
            ImagenDragon(
                nombreRecurso = dragon.imagen,
                descripcion = dragon.nombre,
                modifier = Modifier
                    .padding(10.dp)
                    .size(72.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = dragon.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MarronOscuro
                )
                Text(
                    text = "${encuentro.lugar} · ${encuentro.fecha.formatoCorto()}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Marron,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IndicadorPeligrosidad(
                    peligrosidad = encuentro.peligrosidad,
                    modifier = Modifier.padding(top = 4.dp, end = 12.dp)
                )
            }
            if (modoBorrado) {
                Checkbox(
                    checked = seleccionado,
                    onCheckedChange = onSeleccionChange,
                    colors = CheckboxDefaults.colors(
                        checkedColor = VerdeBosque,
                        checkmarkColor = PergaminoClaro,
                        uncheckedColor = Marron
                    )
                )
            }
        }
    }
}


@Composable
fun FilaEstadisticas(estadisticas: Estadisticas, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CajaEstadistica("Encuentros", estadisticas.total.toString(), Modifier.weight(1f))
        CajaEstadistica("Peligro medio", estadisticas.mediaTexto, Modifier.weight(1f))
        CajaEstadistica("Peligro máximo", estadisticas.maximaTexto, Modifier.weight(1f))
    }
}

@Composable
private fun CajaEstadistica(titulo: String, valor: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(FormaPagina)
            .background(PergaminoOscuro)
            .padding(vertical = 8.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = valor,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = VerdeBosque
        )
        Text(
            text = titulo,
            style = MaterialTheme.typography.labelMedium,
            color = Marron
        )
    }
}

@Composable
fun CajaTexto(texto: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(FormaPagina)
            .background(PergaminoClaro)
            .border(1.dp, Oro, FormaPagina)
    ) {
        Text(
            text = texto,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MarronOscuro
        )
    }
}
