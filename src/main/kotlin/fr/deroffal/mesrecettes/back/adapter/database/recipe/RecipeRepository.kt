package fr.deroffal.mesrecettes.back.adapter.database.recipe

import fr.deroffal.mesrecettes.back.domain.model.recipe.DishType
import fr.deroffal.mesrecettes.back.domain.model.recipe.Recipe
import fr.deroffal.mesrecettes.back.domain.model.recipe.Source
import fr.deroffal.mesrecettes.back.domain.services.recipe.RecipeRepositoryAdapter
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.*

@Repository
class RecipeRepository(
    private val mongoTemplate: ReactiveMongoTemplate,
    private val recipeReactiveMongoRepository: RecipeReactiveMongoRepository,
    private val recipeMapper: RecipeMapper

) : RecipeRepositoryAdapter {

    override fun findById(id: String) = recipeReactiveMongoRepository.findById(id).map { recipeMapper.toRecipe(it) }

    override fun save(recipe: Recipe) = recipeReactiveMongoRepository.save(recipeMapper.toRecipeEntity(recipe)).map { recipeMapper.toRecipe(it) }

    override fun saveAll(recipes: Iterable<Recipe>) =
        recipeReactiveMongoRepository.saveAll(recipeMapper.toRecipesEntities(recipes)).map { recipeMapper.toRecipe(it) }

    override fun findAllByDishTypeAndSource(dishType: Optional<DishType>, source: Optional<Source>): Flux<Recipe> {
        val criterias = mutableListOf<Criteria>()
        if (dishType.isPresent) {
            criterias += Criteria.where("dishType").`is`(dishType.get())
        }
        if (source.isPresent) {
            criterias += Criteria.where("source").`is`(source.get())
        }
        val recipes = if (criterias.isNotEmpty()) {
            val queryCriterias = criterias.reduce { acc: Criteria, criteria: Criteria -> acc.andOperator(criteria) }
            findByCriteria(queryCriterias)
        } else {
            recipeReactiveMongoRepository.findAll()
        }
        return recipes.map { recipeMapper.toRecipe(it) }
    }

    private fun findByCriteria(queryCriterias: Criteria): Flux<RecipeEntity> {
        return mongoTemplate.find(query(queryCriterias), RecipeEntity::class.java)
    }
}

interface RecipeReactiveMongoRepository : ReactiveMongoRepository<RecipeEntity, String>
