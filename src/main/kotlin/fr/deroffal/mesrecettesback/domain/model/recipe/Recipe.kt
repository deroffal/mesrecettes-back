package fr.deroffal.mesrecettesback.domain.model.recipe

import java.time.Instant

data class Recipe(
    var id: String? = null,
    var name: String,
    var description: String? = null,
    var creationDate: Instant = Instant.now(),
    var dishType: DishType,
    var source: Source,
    var webSource: String
)
