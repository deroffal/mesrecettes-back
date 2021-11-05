package fr.deroffal.mesrecettes.back.adapter.database.recipe

import fr.deroffal.mesrecettes.back.MapperConfiguration
import fr.deroffal.mesrecettes.back.domain.model.recipe.Recipe
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(config = MapperConfiguration::class)
interface RecipeMapper {

    fun toRecipe(recipe: RecipeEntity): Recipe

    @Mapping(target = "id", ignore = true)
    fun toRecipeEntity(recipe: Recipe): RecipeEntity

    fun toRecipesEntities(recipe: Iterable<Recipe>): Iterable<RecipeEntity>

}
