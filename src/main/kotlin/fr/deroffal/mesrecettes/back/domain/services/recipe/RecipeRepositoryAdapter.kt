package fr.deroffal.mesrecettes.back.domain.services.recipe

import fr.deroffal.mesrecettes.back.domain.model.recipe.DishType
import fr.deroffal.mesrecettes.back.domain.model.recipe.Recipe
import fr.deroffal.mesrecettes.back.domain.model.recipe.Source
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

interface RecipeRepositoryAdapter {

    fun findById(id: String): Mono<Recipe>
    fun save(recipe: Recipe): Mono<Recipe>
    fun saveAll(recipes: Iterable<Recipe>): Flux<Recipe>

    fun findAllByDishTypeAndSource(dishType: Optional<DishType>, source: Optional<Source>): Flux<Recipe>
}
