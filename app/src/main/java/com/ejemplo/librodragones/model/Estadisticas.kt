package com.ejemplo.librodragones.model

import java.time.LocalDate


data class Estadisticas(
    val total: Int,
    val peligrosidadMedia: Double,
    val peligrosidadMaxima: Int,
    val ultimoEncuentro: LocalDate?
) {
    val mediaTexto: String get() = if (total == 0) "-" else "%.1f".format(peligrosidadMedia)
    val maximaTexto: String get() = if (total == 0) "-" else peligrosidadMaxima.toString()
}

fun List<Encuentro>.estadisticas(): Estadisticas = Estadisticas(
    total = size,
    peligrosidadMedia = if (isEmpty()) 0.0 else map { it.peligrosidad }.average(),
    peligrosidadMaxima = maxOfOrNull { it.peligrosidad } ?: 0,
    ultimoEncuentro = maxOfOrNull { it.fecha }
)
