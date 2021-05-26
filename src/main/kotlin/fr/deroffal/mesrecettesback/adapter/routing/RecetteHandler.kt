package fr.deroffal.mesrecettesback.adapter.routing

import fr.deroffal.mesrecettesback.domain.model.Recette
import fr.deroffal.mesrecettesback.domain.services.RecetteService
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.net.URI
import java.util.*

@Component
class RecetteHandler(var recetteService: RecetteService) {

    var notFound = notFound().build()

    fun list(request: ServerRequest): Mono<ServerResponse> =
        ok()
            .body(recetteService.findAll(), Recette::class.java)

    fun getRecette(request: ServerRequest): Mono<ServerResponse> =
        UUID.fromString(request.pathVariable("id")).toMono()
            .flatMap { recetteService.findById(it) }
            .flatMap {
                ok()
                    .contentType(APPLICATION_JSON)
                    .body(Mono.just(it), Recette::class.java)
            }.switchIfEmpty(notFound)

    fun createRecette(request: ServerRequest): Mono<ServerResponse> =
        request.bodyToMono(Recette::class.java)
            .flatMap { recetteService.save(it) }
            .flatMap { created(URI("/recette/${it.id}")).build() }
}
