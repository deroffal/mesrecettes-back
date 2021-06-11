package fr.deroffal.mesrecettesback.domain.services.administration

import fr.deroffal.mesrecettesback.domain.model.recipe.DishType.*
import fr.deroffal.mesrecettesback.domain.model.recipe.Recipe
import fr.deroffal.mesrecettesback.domain.model.recipe.Source.WEB
import fr.deroffal.mesrecettesback.domain.services.recipe.RecipeService
import org.springframework.stereotype.Service

@Service
class AdministrationService(
    private val recipeService: RecipeService
) {

    fun initDatabase() = recipeService.saveAll(
        listOf(
            Recipe(
                name = "Carpaccio de St-Jacques au citron et fleur de sel à la vanille",
                description = "St Jacques crue avec du citron. Frais et très bon!",
                dishType = STARTER,
                source = WEB,
                webSource = "http://www.recettes-bretonnes.fr/entrees/st-jacques-vanille-citron.html"
            ),
            Recipe(
                name = "Galettes",
                dishType = MAIN_COURSE,
                source = WEB,
                webSource = "https://www.cuisineactuelle.fr/recettes/galette-bretonne-salee-206203"
            ),
            Recipe(
                name = "Pâte à crêpes",
                dishType = DESSERT,
                source = WEB,
                webSource = "https://www.marmiton.org/recettes/recette_pate-a-crepes_12372.aspx"
            )
        )
    )
}
