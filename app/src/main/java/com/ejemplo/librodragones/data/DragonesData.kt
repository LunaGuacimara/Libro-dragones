package com.ejemplo.librodragones.data

import androidx.compose.ui.graphics.Color
import com.ejemplo.librodragones.model.Dragon


object DragonesData {

    val dragones: List<Dragon> = listOf(
        Dragon(
            id = 1,
            nombre = "Furia Nocturna — Night Fury",
            clase = "Embestida (Strike)",
            habilidades = listOf("Vuelo a gran velocidad", "Disparos de plasma", "Sigilo", "Gran inteligencia"),
            descripcion = "Dragón extremadamente raro, de color oscuro y gran agilidad. Destaca por su velocidad, inteligencia y capacidad para lanzar potentes explosiones de plasma.",
            imagen = "furia_nocturna",
            color = Color(0xFF3E4A59)
        ),
        Dragon(
            id = 2,
            nombre = "Furia Luminosa — Light Fury",
            clase = "Embestida (Strike)",
            habilidades = listOf("Disparos de plasma", "Camuflaje", "Vuelo ágil", "Gran velocidad"),
            descripcion = "Dragón de aspecto claro y elegante, adaptado para desplazarse con rapidez. Puede camuflarse con el entorno y utilizar sus disparos de plasma para defenderse.",
            imagen = "furia_luminosa",
            color = Color(0xFF9AA5A8)
        ),
        Dragon(
            id = 3,
            nombre = "Nadder Mortífero — Deadly Nadder",
            clase = "Rastreador (Tracker)",
            habilidades = listOf("Vuelo", "Espinas afiladas", "Olfato desarrollado", "Aliento de fuego"),
            descripcion = "Dragón de colores vivos y cuerpo cubierto de espinas. Tiene un olfato muy desarrollado y puede utilizar su cola para defenderse de posibles amenazas.",
            imagen = "nadder_mortifero",
            color = Color(0xFF3F6E8C)
        ),
        Dragon(
            id = 4,
            nombre = "Gronckle",
            clase = "Roca (Boulder)",
            habilidades = listOf("Vuelo", "Resistencia física", "Bolas de lava", "Gran fuerza"),
            descripcion = "Dragón robusto y de cuerpo redondeado. Aunque no es especialmente rápido, posee una gran resistencia y puede expulsar proyectiles de roca fundida.",
            imagen = "gronckle",
            color = Color(0xFF8A6A3B)
        ),
        Dragon(
            id = 5,
            nombre = "Pesadilla Monstruosa — Monstrous Nightmare",
            clase = "Fogonero (Stoker)",
            habilidades = listOf("Fuego", "Vuelo", "Resistencia", "Combustión corporal"),
            descripcion = "Dragón de aspecto intimidante, conocido por su capacidad para cubrir su cuerpo de llamas. Es territorial y puede resultar difícil de controlar.",
            imagen = "pesadilla_monstruosa",
            color = Color(0xFFB5401F)
        ),
        Dragon(
            id = 6,
            nombre = "Cremallerus Monstruoso — Hideous Zippleback",
            clase = "Misterio (Mystery)",
            habilidades = listOf("Dos cabezas", "Producción de gas", "Explosiones", "Vuelo"),
            descripcion = "Dragón de dos cabezas que trabajan de forma independiente. Una produce un gas inflamable y la otra puede generar la chispa que lo hace explotar.",
            imagen = "zippleback",
            color = Color(0xFF6E8B3D)
        ),
        Dragon(
            id = 7,
            nombre = "Cortatormentas — Stormcutter",
            clase = "Embestida (Strike)",
            habilidades = listOf("Vuelo con cuatro alas", "Maniobrabilidad", "Gran visión", "Vuelo acrobático"),
            descripcion = "Dragón de gran tamaño con cuatro alas que le permiten realizar maniobras aéreas excepcionales. Su silueta recuerda a la de un ave rapaz.",
            imagen = "cortatormentas",
            color = Color(0xFF3C7A8A)
        ),
        Dragon(
            id = 8,
            nombre = "Skrill",
            clase = "Embestida (Strike)",
            habilidades = listOf("Electricidad", "Vuelo veloz", "Ataques eléctricos", "Resistencia"),
            descripcion = "Dragón peligroso que puede canalizar electricidad y aprovechar las tormentas para aumentar su poder. Su cuerpo está cubierto de espinas.",
            imagen = "skrill",
            color = Color(0xFF5C6BA8)
        ),
        Dragon(
            id = 9,
            nombre = "Muerte Susurrante — Whispering Death",
            clase = "Roca (Boulder)",
            habilidades = listOf("Excavar túneles", "Desplazamiento subterráneo", "Dientes giratorios", "Gran fuerza"),
            descripcion = "Dragón de cuerpo alargado que puede desplazarse bajo tierra. Posee una mandíbula circular repleta de dientes que le permite abrirse paso entre la roca.",
            imagen = "muerte_susurrante",
            color = Color(0xFF8C5A2B)
        ),
        Dragon(
            id = 10,
            nombre = "Terrible Terror — Terrible Terror",
            clase = "Fogonero (Stoker)",
            habilidades = listOf("Aliento de fuego", "Gran precisión", "Vuelo", "Tamaño reducido"),
            descripcion = "Uno de los dragones más pequeños conocidos, aproximadamente del tamaño de un gato. A pesar de su aspecto inofensivo, puede lanzar fuego con gran precisión y esconderse fácilmente en pequeños refugios.",
            imagen = "terrible_terror",
            color = Color(0xFF588D37)
        ),
        Dragon(
            id = 11,
            nombre = "Rompehuesos — Boneknapper",
            clase = "Misterio (Mystery)",
            habilidades = listOf("Armadura de huesos", "Rugido ensordecedor", "Aliento de fuego", "Gran fuerza"),
            descripcion = "Dragón de aspecto misterioso que cubre su cuerpo con huesos para crear una armadura protectora. Busca constantemente huesos que encajen con su armadura y puede utilizar su cola para defenderse.",
            imagen = "rompehuesos",
            color = Color(0xFFE1DBD2)
        ),
        Dragon(
            id = 12,
            nombre = "Muerte Roja — Red Death",
            clase = "Fogonero (Stoker)",
            habilidades = listOf("Aliento de fuego de metano", "Piel resistente a la lava", "Gran fuerza", "Control de otros dragones"),
            descripcion = "Dragón gigantesco que habita en volcanes. Su enorme tamaño y resistencia lo convierten en una criatura temible. Puede expulsar grandes llamaradas y ejercer control sobre otros dragones.",
            imagen = "muerte_roja",
            color = Color(0xFF520B0B)
        )

    )
}
