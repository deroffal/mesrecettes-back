package fr.deroffal.mesrecettesback.domain.services.recipe

import fr.deroffal.mesrecettesback.domain.model.recipe.Recipe
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface RecipeRepository : ReactiveMongoRepository<Recipe, String>

