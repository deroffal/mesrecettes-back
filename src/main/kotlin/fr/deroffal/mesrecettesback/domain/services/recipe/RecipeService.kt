package fr.deroffal.mesrecettesback.domain.services.recipe

import fr.deroffal.mesrecettesback.domain.model.recipe.DishType
import fr.deroffal.mesrecettesback.domain.model.recipe.Recipe
import fr.deroffal.mesrecettesback.domain.model.recipe.Source
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.*

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository,
    private val mongoTemplate: ReactiveMongoTemplate
) {

    fun findById(id: String) = recipeRepository.findById(id)

    fun findAll() = recipeRepository.findAll()

    fun save(recipe: Recipe) = recipeRepository.save(recipe)

    fun saveAll(recipes: Iterable<Recipe>) = recipeRepository.saveAll(recipes)

    fun findAllByDishTypeAndSource(dishType: Optional<DishType>, source: Optional<Source>): Flux<Recipe> {
        val criterias = mutableListOf<Criteria>()
        if (dishType.isPresent) {
            criterias += where("dishType").`is`(dishType.toString())
        }
        if (source.isPresent) {
            criterias += where("source").`is`(source.toString())
        }

        return if (criterias.isNotEmpty()) {
            val queryCriterias = criterias.reduce { acc: Criteria, criteria: Criteria -> acc.andOperator(criteria) }
            findByCriteria(queryCriterias)
        } else {
            findAll()
        }
    }

    private fun findByCriteria(queryCriterias: Criteria): Flux<Recipe> {
        return mongoTemplate.find(query(queryCriterias), Recipe::class.java)
    }

}
