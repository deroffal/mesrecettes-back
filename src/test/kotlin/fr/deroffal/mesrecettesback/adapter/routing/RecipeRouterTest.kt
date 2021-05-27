package fr.deroffal.mesrecettesback.adapter.routing

import fr.deroffal.mesrecettesback.domain.services.RecipeService
import fr.deroffal.mesrecettesback.model.RecipeBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.time.Instant
import java.util.*


@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [RecipeRouter::class, RecipeHandler::class])
@WebFluxTest
internal class RecipeRouterTest(
    private val context: ApplicationContext
) {

    @MockBean
    lateinit var recipeService: RecipeService

    var webTestClient: WebTestClient? = null

    @BeforeEach
    fun setup() {
        webTestClient = WebTestClient.bindToApplicationContext(context).build()
    }

    @Test
    fun `Recuperation d'une recette par id`() {
        //given:
        val id = UUID.randomUUID()
        val now = Instant.parse("2021-05-03T21:37:00.000Z")
        val recette = Mono.just(RecipeBuilder(creationDate = now).build()
            .apply {
                this.id = id
            })
        `when`(recipeService.findById(id)).thenReturn(recette)

        //when:
        val exchange = webTestClient!!.get()
            .uri("/recette/$id").accept(APPLICATION_JSON)
            .exchange()

        //then:
        exchange
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.name").isEqualTo("nom recette")
            .jsonPath("$.description").isEqualTo("description recette")
            .jsonPath("$.id").isEqualTo(id.toString())
            .jsonPath("$.creationDate").isEqualTo("2021-05-03T21:37:00Z")
            .jsonPath("$.dishType").isEqualTo("MAIN_COURSE")
            .jsonPath("$.source").isEqualTo("WEB")
            .jsonPath("$.webSource").isEqualTo("https://www.marmiton.org/maRecette")
    }

    @Test
    fun `Recuperation d'une recette par id - id inconnu`() {
        //given:
        val id = UUID.randomUUID()
        `when`(recipeService.findById(id)).thenReturn(Mono.empty())

        //when:
        val exchange = webTestClient!!.get()
            .uri("/recette/$id").accept(APPLICATION_JSON)
            .exchange()

        //then:
        exchange
            .expectStatus().isNotFound
    }

}
