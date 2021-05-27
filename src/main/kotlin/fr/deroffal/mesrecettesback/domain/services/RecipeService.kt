package fr.deroffal.mesrecettesback.domain.services

import fr.deroffal.mesrecettesback.domain.model.Recipe
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class RecipeService(
    private var recipeRepository: RecipeRepository,
    private var r2dbcEntityTemplate : R2dbcEntityTemplate
) {

    fun findById(id: UUID) = recipeRepository.findById(id)

    fun save(recipe: Recipe): Mono<Recipe> = recipeRepository.save(recipe)

    fun findAll() = recipeRepository.findAll()

}
