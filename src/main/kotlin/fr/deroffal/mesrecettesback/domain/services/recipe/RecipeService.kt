package fr.deroffal.mesrecettesback.domain.services.recipe

import fr.deroffal.mesrecettesback.domain.model.recipe.DishType
import fr.deroffal.mesrecettesback.domain.model.recipe.Recipe
import fr.deroffal.mesrecettesback.domain.model.recipe.Source
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RecipeService(
    private val recipeRepositoryAdapter: RecipeRepositoryAdapter
) {

    fun findById(id: String) = recipeRepositoryAdapter.findById(id)

    fun save(recipe: Recipe) = recipeRepositoryAdapter.save(recipe)

    fun saveAll(recipes: Iterable<Recipe>) = recipeRepositoryAdapter.saveAll(recipes)

    fun findAllByDishTypeAndSource(dishType: Optional<DishType>, source: Optional<Source>) =
        recipeRepositoryAdapter.findAllByDishTypeAndSource(dishType, source)


}
