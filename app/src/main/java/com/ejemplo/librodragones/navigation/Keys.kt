package com.ejemplo.librodragones.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable



@Serializable
data object EnciclopediaKey : NavKey

@Serializable
data class FichaDragonKey(val dragonId: Int) : NavKey

@Serializable
data object RegistroKey : NavKey

@Serializable
data object NuevoEncuentroKey : NavKey

@Serializable
data class DetalleEncuentroKey(val encuentroId: Int) : NavKey
