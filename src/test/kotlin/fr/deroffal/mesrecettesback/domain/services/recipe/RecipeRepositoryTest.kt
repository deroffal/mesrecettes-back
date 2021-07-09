package fr.deroffal.mesrecettesback.domain.services.recipe

import fr.deroffal.mesrecettesback.domain.model.recipe.DishType.*
import fr.deroffal.mesrecettesback.domain.model.recipe.Recipe
import fr.deroffal.mesrecettesback.domain.model.recipe.Source.OTHER
import fr.deroffal.mesrecettesback.domain.model.recipe.Source.WEB
import fr.deroffal.mesrecettesback.model.RecipeBuilder
import io.kotest.matchers.collections.haveSize
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.should
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import reactor.core.publisher.Flux
import java.time.Duration

/**
 * https://kotest.io/docs/quickstart/
 * https://phauer.com/2018/best-practices-unit-testing-kotlin/
 */
//@DataMongoTest
internal class RecipeRepositoryTest(
    @Autowired private val recipeRepository: RecipeRepository
) {

    private val blockTimeoutDuration = Duration.ofSeconds(10)

    @BeforeEach
    fun setup() {
        recipeRepository.deleteAll()
            .doOnNext { initData().blockLast(blockTimeoutDuration) }
    }

    private fun initData(): Flux<Recipe> {
        val recipe11 = RecipeBuilder(name = "recipe11", dishType = STARTER, source = WEB).build()
        val recipe12 = RecipeBuilder(name = "recipe12", dishType = STARTER, source = WEB).build()
        val recipe21 = RecipeBuilder(name = "recipe21", dishType = MAIN_COURSE, source = WEB).build()
        val recipe22 = RecipeBuilder(name = "recipe22", dishType = MAIN_COURSE, source = WEB).build()
        val recipe31 = RecipeBuilder(name = "recipe31", dishType = DESSERT, source = WEB).build()
        val recipe32 = RecipeBuilder(name = "recipe32", dishType = DESSERT, source = WEB).build()
        val recipe41 = RecipeBuilder(name = "recipe41", dishType = STARTER, source = OTHER).build()
        val recipe42 = RecipeBuilder(name = "recipe42", dishType = STARTER, source = OTHER).build()
        val recipe51 = RecipeBuilder(name = "recipe51", dishType = MAIN_COURSE, source = OTHER).build()
        val recipe52 = RecipeBuilder(name = "recipe52", dishType = MAIN_COURSE, source = OTHER).build()
        val recipe61 = RecipeBuilder(name = "recipe61", dishType = DESSERT, source = OTHER).build()
        val recipe62 = RecipeBuilder(name = "recipe62", dishType = DESSERT, source = OTHER).build()

        return recipeRepository.saveAll(
            listOf(recipe11, recipe21, recipe31, recipe41, recipe51, recipe61, recipe12, recipe22, recipe32, recipe42, recipe52, recipe62)
        )
    }

    @Disabled
    @Test
    fun `findAll should return everything`() {
        //when:
        val recipes = recipeRepository.findAll().toIterable().toList()

        //then:
        recipes should haveSize(12)

        recipes.map { it.name } shouldContainExactlyInAnyOrder listOf(
            "recipe11",
            "recipe12",
            "recipe21",
            "recipe22",
            "recipe31",
            "recipe32",
            "recipe41",
            "recipe42",
            "recipe51",
            "recipe52",
            "recipe61",
            "recipe62"
        )

    }

}
