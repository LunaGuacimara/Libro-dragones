package com.ejemplo.librodragones.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter


data class Encuentro(
    val id: Int,
    val dragonId: Int,
    val lugar: String,
    val fecha: LocalDate,
    val peligrosidad: Int, // 0..100
    val habilidadesObservadas: List<String>,
    val observaciones: String
)

private val FORMATO_FECHA: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

fun LocalDate.formatoCorto(): String = format(FORMATO_FECHA)

fun nivelPeligro(peligrosidad: Int): String = when {
    peligrosidad < 25 -> "Inofensivo"
    peligrosidad < 50 -> "Precaución"
    peligrosidad < 75 -> "Peligroso"
    else -> "Extremo"
}
