package fr.deroffal.mesrecettesback.domain.services.recipe

import fr.deroffal.mesrecettesback.domain.model.recipe.Recipe
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*


interface RecipeRepository : ReactiveCrudRepository<Recipe, UUID>

