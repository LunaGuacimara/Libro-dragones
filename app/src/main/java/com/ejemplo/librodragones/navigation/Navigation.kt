package com.ejemplo.librodragones.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.ejemplo.librodragones.data.DragonesData
import com.ejemplo.librodragones.data.EncuentrosData
import com.ejemplo.librodragones.model.Encuentro
import com.ejemplo.librodragones.ui.pantallas.CrearEncuentroScreen
import com.ejemplo.librodragones.ui.pantallas.DetalleEncuentroScreen
import com.ejemplo.librodragones.ui.pantallas.EnciclopediaScreen
import com.ejemplo.librodragones.ui.pantallas.FichaDragonScreen
import com.ejemplo.librodragones.ui.pantallas.RegistroScreen


@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(EnciclopediaKey)

    val dragones = remember { DragonesData.dragones }
    val encuentros = remember {
        mutableStateListOf<Encuentro>().apply { addAll(EncuentrosData.encuentrosIniciales) }
    }

    fun irAPestana(destino: NavKey) {
        if (backStack.size == 1 && backStack[0] != destino) backStack[0] = destino
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<EnciclopediaKey> {
                EnciclopediaScreen(
                    dragones = dragones,
                    onDragonClick = { dragon -> backStack.add(FichaDragonKey(dragon.id)) },
                    onIrARegistro = { irAPestana(RegistroKey) }
                )
            }

            entry<FichaDragonKey> { key ->
                val dragon = dragones.firstOrNull { it.id == key.dragonId }
                if (dragon != null) {
                    FichaDragonScreen(
                        dragon = dragon,
                        encuentros = encuentros.filter { it.dragonId == dragon.id },
                        onBack = { backStack.removeLastOrNull() },
                        onEncuentroClick = { encuentro ->
                            backStack.add(DetalleEncuentroKey(encuentro.id))
                        }
                    )
                }
            }

            entry<RegistroKey> {
                RegistroScreen(
                    dragones = dragones,
                    encuentros = encuentros,
                    onAnadir = { backStack.add(NuevoEncuentroKey) },
                    onEliminar = { ids -> encuentros.removeAll { it.id in ids } },
                    onEncuentroClick = { encuentro ->
                        backStack.add(DetalleEncuentroKey(encuentro.id))
                    },
                    onIrAEnciclopedia = { irAPestana(EnciclopediaKey) }
                )
            }

            entry<NuevoEncuentroKey> {
                CrearEncuentroScreen(
                    dragones = dragones,
                    onGuardar = { nuevo ->
                        val nuevoId = (encuentros.maxOfOrNull { it.id } ?: 0) + 1
                        encuentros.add(nuevo.copy(id = nuevoId))
                        backStack.removeLastOrNull()
                    },
                    onDescartar = { backStack.removeLastOrNull() }
                )
            }

            entry<DetalleEncuentroKey> { key ->
                val encuentro = encuentros.firstOrNull { it.id == key.encuentroId }
                val dragon = dragones.firstOrNull { it.id == encuentro?.dragonId }
                if (encuentro != null && dragon != null) {
                    DetalleEncuentroScreen(
                        encuentro = encuentro,
                        dragon = dragon,
                        encuentrosDelDragon = encuentros.filter { it.dragonId == dragon.id },
                        onBack = { backStack.removeLastOrNull() }
                    )
                }
            }
        }
    )
}
