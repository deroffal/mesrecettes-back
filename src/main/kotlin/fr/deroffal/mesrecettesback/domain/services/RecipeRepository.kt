package fr.deroffal.mesrecettesback.domain.services

import fr.deroffal.mesrecettesback.domain.model.Recipe
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*


interface RecipeRepository : ReactiveCrudRepository<Recipe, UUID>

