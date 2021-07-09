package fr.deroffal.mesrecettesback.domain.services.recipe

import fr.deroffal.mesrecettesback.domain.model.recipe.Recipe
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.core.publisher.Flux

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [RecipeService::class])
internal class RecipeServiceTest(
    @Autowired private val recipeService: RecipeService
) {

    @MockBean
    private lateinit var recipeRepository: RecipeRepository

    @MockBean
    private lateinit var mongoTemplate: ReactiveMongoTemplate

    @Test
    fun `findAll should return everything`() {
        //given:
        val expectedFlux = Flux.just<Recipe>()
        `when`(recipeRepository.findAll()).thenReturn(expectedFlux)

        //when:
        val recipes = recipeService.findAll()

        //then:
        recipes shouldBe expectedFlux
    }

}
