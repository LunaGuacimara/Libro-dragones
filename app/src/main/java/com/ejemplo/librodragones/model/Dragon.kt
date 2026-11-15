package com.ejemplo.librodragones.model

import androidx.compose.ui.graphics.Color

data class Dragon(
    val id: Int,
    val nombre: String,
    val clase: String,
    val habilidades: List<String>,
    val descripcion: String,
    val imagen: String,
    val color: Color
)
