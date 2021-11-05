package fr.deroffal.mesrecettes.back.adapter.routing

import fr.deroffal.mesrecettes.back.domain.model.recipe.Recipe
import fr.deroffal.mesrecettes.back.domain.services.administration.AdministrationService
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class AdministrationHandler(private val administrationService: AdministrationService) {

    fun init(request: ServerRequest): Mono<ServerResponse> =
        ok()
            .contentType(APPLICATION_JSON)
            .body(administrationService.initDatabase(), Recipe::class.java)

}
