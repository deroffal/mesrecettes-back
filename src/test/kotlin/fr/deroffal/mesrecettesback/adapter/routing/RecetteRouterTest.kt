package fr.deroffal.mesrecettesback.adapter.routing

import fr.deroffal.mesrecettesback.domain.model.Recette
import fr.deroffal.mesrecettesback.domain.services.RecetteService
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
@ContextConfiguration(classes = [RecetteRouter::class, RecetteHandler::class])
@WebFluxTest
internal class RecetteRouterTest(
    private val context: ApplicationContext
) {

    @MockBean
    lateinit var recetteService: RecetteService

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
        val recette = Mono.just(Recette("nom recette", "description recette")
            .apply {
                this.id = id
                this.dateCreation = now
            })
        `when`(recetteService.findById(id)).thenReturn(recette)

        //when:
        val exchange = webTestClient!!.get()
            .uri("/recette/$id").accept(APPLICATION_JSON)
            .exchange()

        //then:
        exchange
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.nom").isEqualTo("nom recette")
            .jsonPath("$.description").isEqualTo("description recette")
            .jsonPath("$.id").isEqualTo(id.toString())
            .jsonPath("$.dateCreation").isEqualTo("2021-05-03T21:37:00Z")
    }

    @Test
    fun `Recuperation d'une recette par id - id inconnu`() {
        //given:
        val id = UUID.randomUUID()
        `when`(recetteService.findById(id)).thenReturn(Mono.empty())

        //when:
        val exchange = webTestClient!!.get()
            .uri("/recette/$id").accept(APPLICATION_JSON)
            .exchange()

        //then:
        exchange
            .expectStatus().isNotFound
    }

}
