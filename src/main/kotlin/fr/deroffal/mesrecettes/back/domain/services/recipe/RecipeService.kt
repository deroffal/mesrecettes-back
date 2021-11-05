package fr.deroffal.mesrecettes.back.domain.services.recipe

import fr.deroffal.mesrecettes.back.domain.model.recipe.DishType
import fr.deroffal.mesrecettes.back.domain.model.recipe.Recipe
import fr.deroffal.mesrecettes.back.domain.model.recipe.Source
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
