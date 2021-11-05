package fr.deroffal.mesrecettes.back.adapter.routing

import fr.deroffal.mesrecettes.back.domain.model.recipe.DishType
import fr.deroffal.mesrecettes.back.domain.model.recipe.Recipe
import fr.deroffal.mesrecettes.back.domain.model.recipe.Source
import fr.deroffal.mesrecettes.back.domain.services.recipe.RecipeService
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
class RecipeHandler(val recipeService: RecipeService) {

    val notFound = notFound().build()

    fun list(request: ServerRequest): Mono<ServerResponse> {
        val dishType = request.queryParam("dishType").map { DishType.valueOf(it) }
        val source = request.queryParam("source").map { Source.valueOf(it) }
        val recipes = recipeService.findAllByDishTypeAndSource(dishType, source)
        return ok()
            .contentType(APPLICATION_JSON)
            .body(recipes, Recipe::class.java)
    }

    fun getRecette(request: ServerRequest) =
        request.pathVariable("id").toMono()
            .flatMap { recipeService.findById(it) }
            .flatMap {
                ok()
                    .contentType(APPLICATION_JSON)
                    .body(Mono.just(it), Recipe::class.java)
            }.switchIfEmpty(notFound)

    fun createRecette(request: ServerRequest) =
        request.bodyToMono(Recipe::class.java)
            .flatMap { recipeService.save(it) }
            .flatMap { created(URI("/recette/${it.id}")).build() }
}
