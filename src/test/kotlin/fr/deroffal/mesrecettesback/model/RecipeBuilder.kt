package fr.deroffal.mesrecettesback.model

import fr.deroffal.mesrecettesback.domain.model.recipe.Recipe
import fr.deroffal.mesrecettesback.domain.model.recipe.Source
import fr.deroffal.mesrecettesback.domain.model.recipe.DishType
import java.time.Instant

data class RecipeBuilder(
    var name: String = "nom recette",
    var description: String = "description recette",
    var creationDate: Instant = Instant.now(),
    var dishType: DishType = DishType.MAIN_COURSE,
    var source: Source = Source.WEB,
    var webSource: String = "https://www.marmiton.org/maRecette"
) {
    fun build() = Recipe(
        name = name,
        description = description,
        creationDate = creationDate,
        dishType = dishType,
        source = source,
        webSource = webSource
    )
}
