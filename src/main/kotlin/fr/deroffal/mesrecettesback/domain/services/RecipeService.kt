package fr.deroffal.mesrecettesback.domain.services

import fr.deroffal.mesrecettesback.domain.model.DishType
import fr.deroffal.mesrecettesback.domain.model.Recipe
import fr.deroffal.mesrecettesback.domain.model.Source
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria.empty
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.CriteriaDefinition
import org.springframework.data.relational.core.query.Query.query
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.*

@Service
class RecipeService(
    private var recipeRepository: RecipeRepository,
    private var r2dbcEntityTemplate: R2dbcEntityTemplate
) {

    fun findById(id: UUID) = recipeRepository.findById(id)

    fun save(recipe: Recipe) = recipeRepository.save(recipe)

    fun findAllByDishTypeAndSource(dishType: Optional<DishType>, source: Optional<Source>): Flux<Recipe> {
        val criterias = mutableListOf<CriteriaDefinition>()
        if (dishType.isPresent) {
            criterias += where("dishType").`is`(dishType.toString())
        }
        if (dishType.isPresent) {
            criterias += where("source").`is`(source.toString())
        }

        return r2dbcEntityTemplate.select(Recipe::class.java)
            .matching(
                query(
                    empty().and(criterias)
                )
            )
            .all()
    }

}
