package fr.deroffal.mesrecettesback.adapter.routing

import fr.deroffal.mesrecettesback.domain.model.Recette
import fr.deroffal.mesrecettesback.domain.services.RecetteService
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.util.*


@Component
class RecetteHandler(var recetteService: RecetteService) {

    var notFound = ServerResponse.notFound().build()

    fun getRecette(request: ServerRequest): Mono<ServerResponse> {
        val id = UUID.fromString(request.pathVariable("id"))
        val recette = recetteService.findById(id)

        //TODO : sans le unwrap/wrap de Mono ?
//        return ServerResponse.ok()
//            .contentType(APPLICATION_JSON)
//            .body(recette, Recette::class.java)
//            .switchIfEmpty(notFound)
        return recette.flatMap {
            ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(Mono.just(it), Recette::class.java)
        }.switchIfEmpty(notFound)
    }

    fun createRecette(request: ServerRequest): Mono<ServerResponse> = request.bodyToMono(Recette::class.java)
        .flatMap {
            ServerResponse.ok()
                    //TODO voir pour une réponse 201
//            ServerResponse.created(URI(""))
                .contentType(APPLICATION_JSON)
                .body(recetteService.save(it), Recette::class.java)
        }
}
